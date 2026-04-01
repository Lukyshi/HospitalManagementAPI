package com.example.HospitalManagementAPI.controller;

import com.example.HospitalManagementAPI.dto.auth.AuthResponse;
import com.example.HospitalManagementAPI.dto.auth.LoginRequest;
import com.example.HospitalManagementAPI.dto.auth.RegisterRequest;
import com.example.HospitalManagementAPI.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService service;

    public AuthController (AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        String message = service.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", message));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse response = service.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
