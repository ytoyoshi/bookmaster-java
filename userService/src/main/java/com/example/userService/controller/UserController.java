package com.example.userService.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userService.dto.UserRegistrationDto;
import com.example.userService.response.UserResponse;
import com.example.userService.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users/register")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @RequestBody @Valid UserRegistrationDto dto) {
        UserResponse response = userService.createUser(dto);
        return ResponseEntity.ok(response);
    }
}
