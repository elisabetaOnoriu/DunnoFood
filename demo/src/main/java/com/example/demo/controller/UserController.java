package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.dto.UserResponseDTO;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        logger.info("Attempting to register user with username: {}", user.getUsername());
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            logger.warn("Registration failed: Username {} is already taken", user.getUsername());
            return ResponseEntity.badRequest().body(new ErrorResponse() {
                @Override
                public HttpStatusCode getStatusCode() {
                    return null;
                }

                @Override
                public ProblemDetail getBody() {
                    return null;
                }
            });
        }
        User savedUser = userService.registerUser(user);
        logger.info("User {} registered successfully", savedUser.getUsername());
        UserResponseDTO responseDTO = new UserResponseDTO(savedUser.getUsername(), savedUser.getEmail());
        return ResponseEntity.ok(responseDTO);
    }
}


