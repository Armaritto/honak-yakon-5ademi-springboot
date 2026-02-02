package com.stgsporting.honakyakon5ademi.services;

import com.stgsporting.honakyakon5ademi.dtos.QuestionDTO;
import com.stgsporting.honakyakon5ademi.entities.Question;
import com.stgsporting.honakyakon5ademi.entities.Quiz;
import com.stgsporting.honakyakon5ademi.repositories.QuestionRepository;
import com.stgsporting.honakyakon5ademi.repositories.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public QuestionService(QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    public Question createQuestion(QuestionDTO questionDTO, Long quizId) {
        Question question = new Question();
        question.setText(questionDTO.getText());
        question.setType(questionDTO.getType());
        Optional<Quiz> quiz = quizRepository.findQuizById(quizId);
        quiz.ifPresent(question::setQuiz);
        questionRepository.save(question);
        return question;
    }
}
