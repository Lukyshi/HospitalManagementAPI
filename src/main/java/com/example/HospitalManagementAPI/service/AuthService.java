package com.example.HospitalManagementAPI.service;

import com.example.HospitalManagementAPI.dto.auth.AuthResponse;
import com.example.HospitalManagementAPI.dto.auth.LoginRequest;
import com.example.HospitalManagementAPI.dto.auth.RegisterRequest;
import com.example.HospitalManagementAPI.entity.User;
import com.example.HospitalManagementAPI.enums.Role;
import com.example.HospitalManagementAPI.repository.UserRepository;
import com.example.HospitalManagementAPI.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // register user
    public String register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole()));

        userRepository.save(user);

        return "User Registered";
    }

    // login
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Bad credentials"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtService.generateToken(user); // generate jwt

        return new AuthResponse(token);
    }
}
