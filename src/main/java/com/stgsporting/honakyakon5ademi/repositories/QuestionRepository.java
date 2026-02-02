package com.stgsporting.honakyakon5ademi.repositories;

import com.stgsporting.honakyakon5ademi.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findQuestionById(Long id);
}
