package com.stgsporting.honakyakon5ademi.exceptions;

public class UnownedPositionException extends UnownedException {
    public UnownedPositionException(String message) {
        super(message);
    }

    public UnownedPositionException() {
        super("You do not own this position");
    }
}
