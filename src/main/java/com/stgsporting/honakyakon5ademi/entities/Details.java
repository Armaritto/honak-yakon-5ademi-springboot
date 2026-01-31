package com.stgsporting.honakyakon5ademi.entities;

import com.stgsporting.honakyakon5ademi.authentication.Authenticatable;
import org.springframework.security.core.userdetails.UserDetails;

public interface Details extends UserDetails {
    Long getId();

    Authenticatable getAuthenticatable();
}
