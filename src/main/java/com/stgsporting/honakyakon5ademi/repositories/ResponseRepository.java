package com.stgsporting.honakyakon5ademi.repositories;

import com.stgsporting.honakyakon5ademi.entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    Optional<Response> findResponseById(Long id);
}
