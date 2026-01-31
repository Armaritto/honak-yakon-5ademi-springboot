package com.stgsporting.honakyakon5ademi.authentication;

import com.stgsporting.honakyakon5ademi.dtos.RegisterDTO;
import com.stgsporting.honakyakon5ademi.exceptions.UserNotFoundException;
import com.stgsporting.honakyakon5ademi.exceptions.UsernameTakenException;
import com.stgsporting.honakyakon5ademi.services.UserService;

public class UsernameTakenHandler extends RegisterHandler {
    private final UserService userService;

    public UsernameTakenHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handleRequest(RegisterDTO userRegisterDTO) {
        try {
            userService.getUserByUsername(userRegisterDTO.getUsername())
                    .ifPresent(a -> {
                        throw new UsernameTakenException("Username already taken");
                    });
        }
        catch (UserNotFoundException e) {
            super.handleRequest(userRegisterDTO);
        }
    }
}