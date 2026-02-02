package com.stgsporting.honakyakon5ademi.services;

import com.stgsporting.honakyakon5ademi.entities.Answer;
import com.stgsporting.honakyakon5ademi.entities.Question;
import com.stgsporting.honakyakon5ademi.entities.Response;
import com.stgsporting.honakyakon5ademi.repositories.AnswerRepository;
import com.stgsporting.honakyakon5ademi.repositories.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final ResponseRepository responseRepository;

    public AnswerService(AnswerRepository answerRepository, ResponseRepository responseRepository) {
        this.answerRepository = answerRepository;
        this.responseRepository = responseRepository;
    }

    public void createAnswer(String answerText, Question question, Long responseId) {
        Answer answer = new Answer();
        answer.setAnswer(answerText);
        answer.setQuestion(question);
        Optional<Response> response = responseRepository.findResponseById(responseId);
        response.ifPresent(answer::setResponse);
        answerRepository.save(answer);
    }
}
