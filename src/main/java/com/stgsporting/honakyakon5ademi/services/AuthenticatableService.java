package com.stgsporting.honakyakon5ademi.services;


import com.stgsporting.honakyakon5ademi.authentication.Authenticatable;

public interface AuthenticatableService {

    Authenticatable getAuthenticatableById(long id);

    long getAuthenticatableId();

    Authenticatable getAuthenticatable();

    Authenticatable getAuthenticatableByUsername(String username);

    void save(Authenticatable authenticatable);
}
