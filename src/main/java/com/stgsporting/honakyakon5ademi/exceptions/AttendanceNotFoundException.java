package com.stgsporting.honakyakon5ademi.exceptions;

public class AttendanceNotFoundException extends NotFoundException {
    public AttendanceNotFoundException(String message) {
        super(message);
    }

    public AttendanceNotFoundException() {
        super("Attendance not found");
    }
}
