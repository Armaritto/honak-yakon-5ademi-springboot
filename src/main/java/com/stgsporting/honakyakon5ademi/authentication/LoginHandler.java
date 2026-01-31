package com.stgsporting.honakyakon5ademi.authentication;

import com.stgsporting.honakyakon5ademi.dtos.LoginDTO;
import com.stgsporting.honakyakon5ademi.dtos.AuthInfo;

public abstract class LoginHandler {
    protected LoginHandler next;

    public LoginHandler setNextHandler(LoginHandler handler){
        next = handler;
        return handler;
    }
    public abstract AuthInfo handle(LoginDTO userLoginDTO);
}
