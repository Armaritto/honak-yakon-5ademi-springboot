package com.stgsporting.honakyakon5ademi.repositories;

import com.stgsporting.honakyakon5ademi.entities.Response;
import com.stgsporting.honakyakon5ademi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    Optional<Response> findResponseById(Long id);
    List<Response> findByUser(User user);
}
