package com.stgsporting.honakyakon5ademi.controllers;

import com.stgsporting.honakyakon5ademi.dtos.ResponseDTO;
import com.stgsporting.honakyakon5ademi.services.ResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class ResponseController {
    private final ResponseService responseService;

    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createResponse(@RequestBody ResponseDTO responseDTO) {
        responseService.createResponse(responseDTO);
        return ResponseEntity.ok("Response created successfully");
    }
}
