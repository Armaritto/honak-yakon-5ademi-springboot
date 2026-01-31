package com.stgsporting.honakyakon5ademi.exceptions;

public class AlreadyPurchasedException extends RuntimeException {
    public AlreadyPurchasedException(String message) {
        super(message);
    }

    public AlreadyPurchasedException() {
        super("Already purchased");
    }
}
