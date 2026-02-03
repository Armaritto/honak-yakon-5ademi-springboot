package com.stgsporting.honakyakon5ademi.services;

import com.stgsporting.honakyakon5ademi.dtos.QuizDTO;
import com.stgsporting.honakyakon5ademi.entities.Question;
import com.stgsporting.honakyakon5ademi.entities.Quiz;
import com.stgsporting.honakyakon5ademi.entities.User;
import com.stgsporting.honakyakon5ademi.exceptions.QuizNotFoundException;
import com.stgsporting.honakyakon5ademi.repositories.QuizRepository;
import com.stgsporting.honakyakon5ademi.repositories.ResponseRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    public final QuizRepository quizRepository;
    public final QuestionService questionService;
    public final UserService userService;
    public final ResponseRepository responseRepository;

    public QuizService(QuizRepository quizRepository, QuestionService questionService, UserService userService, ResponseRepository responseRepository) {
        this.quizRepository = quizRepository;
        this.questionService = questionService;
        this.userService = userService;
        this.responseRepository = responseRepository;
    }

    public void createQuiz(QuizDTO quizDTO) {
        Quiz quiz = new Quiz();
        quiz.setDate(quizDTO.getDate());
        quizRepository.save(quiz);
        List<Question> questions = new ArrayList<>();
        for(int i=0; i<quizDTO.getQuestionDTOS().size(); i++){
            questions.add(questionService.createQuestion(quizDTO.getQuestionDTOS().get(i),quiz.getId()));
        }
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
    }

    public QuizDTO getQuiz(Long id) {
        Optional<Quiz> quiz = quizRepository.findQuizById(id);
        return getQuizDTO(quiz);
    }

    public QuizDTO getTodayQuiz() {
        Optional<Quiz> quiz = quizRepository.findTodayQuiz();
        return getQuizDTO(quiz);
    }

    public QuizDTO getPreviousQuiz(Date date) {
        Optional<Quiz> quiz = quizRepository.findTopByDateBeforeOrderByDateDesc(date);
        return getQuizDTO(quiz);
    }

    public QuizDTO getQuizByDate(Date date) {
        Optional<Quiz> quiz = quizRepository.findQuizByDate(date);
        return getQuizDTO(quiz);
    }

    private static @NonNull QuizDTO getQuizDTO(Optional<Quiz> quiz) {
        if (quiz.isPresent()) {
            QuizDTO dto = new QuizDTO();
            dto.setDate(quiz.get().getDate());
            dto.setQuestionDTOManually(quiz.get().getQuestions());
            dto.setId(quiz.get().getId());
            return dto;
        }
        throw new QuizNotFoundException("Quiz Not Found");
    }

    public List<Date> getSolvedDates() {
        User user = (User) userService.getAuthenticatable();
        List<Date> dates = new ArrayList<>();
        
        responseRepository.findByUser(user).forEach(response -> {
            if (response.getQuiz() != null && response.getQuiz().getDate() != null) {
                dates.add(response.getQuiz().getDate());
            }
        });
        
        return dates;
    }

    public void deleteQuiz(Long id) {
        Optional<Quiz> quiz = quizRepository.findQuizById(id);
        if (quiz.isEmpty())
            throw new QuizNotFoundException("Quiz not found");
        quizRepository.delete(quiz.get());
    }
}
