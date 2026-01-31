package com.stgsporting.honakyakon5ademi.exceptions;

public class UnownedException extends RuntimeException {
    public UnownedException(String message) {
        super(message);
    }

    public UnownedException() {
        super("You do not own this");
    }
}
