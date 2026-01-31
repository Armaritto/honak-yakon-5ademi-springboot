package com.stgsporting.honakyakon5ademi.exceptions;

public class PriceNotFoundException extends NotFoundException {
    public PriceNotFoundException(String message) {
        super(message);
    }

    public PriceNotFoundException() {
        super("Price not found");
    }
}
