package com.stgsporting.honakyakon5ademi.controllers;

import com.stgsporting.honakyakon5ademi.dtos.LoginDTO;
import com.stgsporting.honakyakon5ademi.exceptions.UserNotFoundException;
import com.stgsporting.honakyakon5ademi.services.AdminAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminAuthenticationController {

    private final AdminAuthenticationService authService;

    AdminAuthenticationController(AdminAuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO userLoginDTO){
        return ResponseEntity.ok().body(
                authService.login(userLoginDTO)
        );
    }
}
