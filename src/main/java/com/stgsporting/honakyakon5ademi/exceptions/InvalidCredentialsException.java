package com.stgsporting.honakyakon5ademi.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Incorrect username or password");
    }
}
