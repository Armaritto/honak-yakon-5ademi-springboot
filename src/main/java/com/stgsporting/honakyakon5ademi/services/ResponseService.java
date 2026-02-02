package com.stgsporting.honakyakon5ademi.services;

import com.stgsporting.honakyakon5ademi.repositories.ResponseRepository;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    private final ResponseRepository responseRepository;

    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }
}
