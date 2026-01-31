package com.stgsporting.honakyakon5ademi.authentication;

import com.stgsporting.honakyakon5ademi.dtos.LoginDTO;
import com.stgsporting.honakyakon5ademi.dtos.AuthInfo;
import com.stgsporting.honakyakon5ademi.exceptions.InvalidCredentialsException;
import com.stgsporting.honakyakon5ademi.exceptions.UserNotFoundException;
import com.stgsporting.honakyakon5ademi.services.AuthenticatableService;

public class CheckIfUserExistsHandler extends LoginHandler {
    private final AuthenticatableService authService;

    public CheckIfUserExistsHandler(AuthenticatableService authService) {
        this.authService = authService;
    }

    @Override
    public AuthInfo handle(LoginDTO loginDTO) {
        if (loginDTO.getUsername() == null){
            throw new NullPointerException("Username is required, can't be null");
        }

        if (authService.getAuthenticatableByUsername(loginDTO.getUsername()) == null){
            throw new InvalidCredentialsException();
        }
        Authenticatable auth = authService.getAuthenticatableByUsername(loginDTO.getUsername());

        return this.setNextHandler(new ValidPasswordHandler(auth)).handle(loginDTO);
    }

}
