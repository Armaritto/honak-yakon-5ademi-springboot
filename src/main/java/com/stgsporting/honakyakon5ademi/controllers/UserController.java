package com.stgsporting.honakyakon5ademi.controllers;

import com.stgsporting.honakyakon5ademi.dtos.UserRegisterDTO;
import com.stgsporting.honakyakon5ademi.entities.User;
import com.stgsporting.honakyakon5ademi.services.UserService;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> create(@RequestBody UserRegisterDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.ok("User Created Successfully");
    }

}
