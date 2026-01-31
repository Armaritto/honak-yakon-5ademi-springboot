package com.stgsporting.honakyakon5ademi.exceptions;

public class UserDeleteException extends RuntimeException {
    public UserDeleteException(String message) {
        super(message);
    }

    public UserDeleteException() {
        super("User could not be deleted");
    }
}
