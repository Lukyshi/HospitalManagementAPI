package com.example.HospitalManagementAPI.service;

import com.example.HospitalManagementAPI.dto.auth.AuthResponse;
import com.example.HospitalManagementAPI.dto.auth.LoginRequest;
import com.example.HospitalManagementAPI.dto.auth.RefreshTokenRequest;
import com.example.HospitalManagementAPI.dto.auth.RegisterRequest;
import com.example.HospitalManagementAPI.entity.RefreshToken;
import com.example.HospitalManagementAPI.entity.User;
import com.example.HospitalManagementAPI.enums.Role;
import com.example.HospitalManagementAPI.repository.RefreshTokenRepository;
import com.example.HospitalManagementAPI.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private RefreshTokenService refreshTokenService;
    private RefreshTokenRepository refreshTokenRepository;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtService jwtService, RefreshTokenService refreshTokenService,
                       RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepository = refreshTokenRepository;
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

        String accessToken = jwtService.generateToken(user); // generate jwt

        String refreshToken = refreshTokenService.createRefreshToken(user.getUsername())
                .getToken();

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshLogin(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException(("Invalid refresh token")));

        if(token.getExpiryDate().before(new Date())) {
            throw new RuntimeException("Refresh token expired");
        }

        User user = userRepository.findByUsername(token.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newAccessToken = jwtService.generateToken(user);

        return new AuthResponse(newAccessToken, refreshToken);

    }
}
