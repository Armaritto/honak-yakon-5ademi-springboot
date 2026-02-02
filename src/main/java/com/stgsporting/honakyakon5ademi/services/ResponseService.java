package com.stgsporting.honakyakon5ademi.services;

import com.stgsporting.honakyakon5ademi.dtos.ResponseDTO;
import com.stgsporting.honakyakon5ademi.entities.Question;
import com.stgsporting.honakyakon5ademi.entities.Quiz;
import com.stgsporting.honakyakon5ademi.entities.Response;
import com.stgsporting.honakyakon5ademi.entities.User;
import com.stgsporting.honakyakon5ademi.exceptions.QuestionNotFoundException;
import com.stgsporting.honakyakon5ademi.exceptions.QuizNotFoundException;
import com.stgsporting.honakyakon5ademi.repositories.QuestionRepository;
import com.stgsporting.honakyakon5ademi.repositories.QuizRepository;
import com.stgsporting.honakyakon5ademi.repositories.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseService {
    private final ResponseRepository responseRepository;
    private final UserService userService;
    private final QuizRepository quizRepository;
    private final AnswerService answerService;
    private final QuestionRepository questionRepository;

    public ResponseService(ResponseRepository responseRepository, UserService userService,
                           QuizRepository quizRepository, AnswerService answerService,
                           QuestionRepository questionRepository) {
        this.responseRepository = responseRepository;
        this.userService = userService;
        this.quizRepository = quizRepository;
        this.answerService = answerService;
        this.questionRepository = questionRepository;
    }

    public void createResponse(ResponseDTO responseDTO) {
        Response response = new Response();
        response.setUser((User) userService.getAuthenticatable());
        Optional<Quiz> quiz = quizRepository.findQuizById(responseDTO.getQuizId());
        if (quiz.isEmpty())
            throw new QuizNotFoundException("Quiz not found");
        response.setQuiz(quiz.get());
        responseRepository.save(response);
        for(int i=0;i<responseDTO.getQuestionIds().size();i++) {
            Optional<Question> question = questionRepository.findQuestionById(responseDTO.getQuestionIds().get(i));
            if(question.isEmpty())
                throw new QuestionNotFoundException("Question Not Found");
            answerService.createAnswer(responseDTO.getAnswerTexts().get(i), question.get(), response.getId());
        }
    }
}
