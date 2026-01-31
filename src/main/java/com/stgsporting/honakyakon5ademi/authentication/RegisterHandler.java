package com.stgsporting.honakyakon5ademi.authentication;

import com.stgsporting.honakyakon5ademi.dtos.RegisterDTO;

public abstract class RegisterHandler {
    protected RegisterHandler nextHandler;

    public RegisterHandler setNextHandler(RegisterHandler nextHandler) {
        this.nextHandler = nextHandler;
        return this.nextHandler;
    }

    public void handleRequest(RegisterDTO userRegisterDTO) {
        if (nextHandler != null) {
            nextHandler.handleRequest(userRegisterDTO);
        }
    }
}
