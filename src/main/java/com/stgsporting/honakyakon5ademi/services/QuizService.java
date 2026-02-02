package com.stgsporting.honakyakon5ademi.services;

import com.stgsporting.honakyakon5ademi.dtos.QuizDTO;
import com.stgsporting.honakyakon5ademi.entities.Question;
import com.stgsporting.honakyakon5ademi.entities.Quiz;
import com.stgsporting.honakyakon5ademi.repositories.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    public final QuizRepository quizRepository;
    public final QuestionService questionService;

    public QuizService(QuizRepository quizRepository, QuestionService questionService) {
        this.quizRepository = quizRepository;
        this.questionService = questionService;
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
}
