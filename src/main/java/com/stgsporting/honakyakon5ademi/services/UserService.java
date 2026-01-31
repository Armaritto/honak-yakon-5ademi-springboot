package com.stgsporting.honakyakon5ademi.services;


import com.stgsporting.honakyakon5ademi.entities.User;
import com.stgsporting.honakyakon5ademi.authentication.Authenticatable;
import com.stgsporting.honakyakon5ademi.entities.*;
import com.stgsporting.honakyakon5ademi.exceptions.*;
import com.stgsporting.honakyakon5ademi.repositories.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements AuthenticatableService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User getAuthenticatableById(long id) {
        return getUserById(id).orElseThrow(UserNotFoundException::new);
    }

    public long getAuthenticatableId() {
        return getAuthenticatable().getId();
    }

    public Authenticatable getAuthenticatable() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetail))
            throw new UnauthorizedAccessException();

        return  ((Details) authentication.getPrincipal()).getAuthenticatable();
    }

    public User getAuthenticatableByUsername(String username){
        if (username == null || username.isEmpty())
            throw new NullPointerException("Username cannot be empty");

        return getUserByUsername(username)
                .orElseThrow(InvalidCredentialsException::new);
    }
    public void save(Authenticatable user) {
        userRepository.save((User) user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUsersByUsername(username);
    }


    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }
}
