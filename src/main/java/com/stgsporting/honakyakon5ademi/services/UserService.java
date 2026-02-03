package com.stgsporting.honakyakon5ademi.services;

import com.stgsporting.honakyakon5ademi.dtos.UserRegisterDTO;
import com.stgsporting.honakyakon5ademi.entities.User;
import com.stgsporting.honakyakon5ademi.authentication.Authenticatable;
import com.stgsporting.honakyakon5ademi.entities.*;
import com.stgsporting.honakyakon5ademi.exceptions.*;
import com.stgsporting.honakyakon5ademi.repositories.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements AuthenticatableService {

    private final UserRepository userRepository;
    private final KhedmaService khedmaService;

    public UserService(UserRepository userRepository, KhedmaService khedmaService) {
        this.userRepository = userRepository;
        this.khedmaService = khedmaService;
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

    @Transactional
    public void createUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();

        validateUsername(userRegisterDTO.getUsername());
        user.setUsername(userRegisterDTO.getUsername());

        validatePassword(userRegisterDTO.getPassword());
        user.setPassword(userRegisterDTO.getPassword());

        Khedma khedma = khedmaService.getKhedmaById(userRegisterDTO.getKhedmaId());
        user.setKhedma(khedma);

        save(user);
    }

    private void validateUsername(String username) {
        if (username == null || username.isEmpty())
            throw new UsernameTakenException("Username cannot be empty");

        if(userRepository.existsByUsername(username)) {
            throw new UsernameTakenException("Username already exists");
        }
    }
    private void validatePassword(String password) {
        if (password == null || password.isEmpty())
            throw new ChangePasswordException("Password cannot be empty");

        if (password.length() < 4 || password.length() > 64)
            throw new ChangePasswordException("Password must be between 6 and 64 characters");
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }
}
