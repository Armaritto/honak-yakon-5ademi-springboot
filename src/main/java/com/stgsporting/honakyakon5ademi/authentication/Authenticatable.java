package com.stgsporting.honakyakon5ademi.authentication;

import com.stgsporting.honakyakon5ademi.entities.Khedma;

public interface Authenticatable {
    Long getId();
    String getUsername();
    String getPassword();
}
