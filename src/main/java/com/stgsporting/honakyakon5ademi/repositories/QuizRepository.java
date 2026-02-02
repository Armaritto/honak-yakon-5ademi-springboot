package com.stgsporting.honakyakon5ademi.repositories;

import com.stgsporting.honakyakon5ademi.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findQuizById(long id);

    @Query("SELECT q FROM QUIZZES q WHERE q.date = CURRENT_DATE")
    Optional<Quiz> findTodayQuiz();

    Optional<Quiz> findTopByDateBeforeOrderByDateDesc(Date date);

    Optional<Quiz> findQuizByDate(Date date);
}
