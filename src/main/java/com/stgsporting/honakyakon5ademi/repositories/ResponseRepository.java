package com.stgsporting.honakyakon5ademi.repositories;

import com.stgsporting.honakyakon5ademi.dtos.UserResponseDTO;
import com.stgsporting.honakyakon5ademi.entities.Response;
import com.stgsporting.honakyakon5ademi.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    Optional<Response> findResponseById(Long id);
    List<Response> findByUser(User user);

    @Query(value = "SELECT u.username, k.name as khedmaName, GROUP_CONCAT(a.answer SEPARATOR ',') as answerTexts, r.checkbox " +
            "FROM responses r " +
            "JOIN quizzes q ON r.quiz_id = q.id " +
            "JOIN users u ON r.user_id = u.id " +   
            "JOIN khedmas k ON u.khedma_id = k.id " +
            "LEFT JOIN answers a ON a.response_id = r.id " +
            "WHERE q.date = :date " +
            "GROUP BY r.id, r.checkbox",
            nativeQuery = true)
    Page<UserResponseDTO> getResponseAndAnswerByDate(Pageable pageable, @Param("date") Date date);

    @Query(value = "SELECT u.username, k.name as khedmaName, GROUP_CONCAT(a.answer SEPARATOR ',') as answerTexts, r.checkbox " +
            "FROM responses r " +
            "JOIN quizzes q ON r.quiz_id = q.id " +
            "JOIN users u ON r.user_id = u.id " +   
            "JOIN khedmas k ON u.khedma_id = k.id " +
            "LEFT JOIN answers a ON a.response_id = r.id " +
            "WHERE q.date = :date AND k.id = :khedmaId " +
            "GROUP BY r.id, r.checkbox",
            nativeQuery = true)
    Page<UserResponseDTO> getResponseAndAnswerByDateAndKhedma(Pageable pageable, @Param("date") Date date, @Param("khedmaId") Long khedmaId);
}
