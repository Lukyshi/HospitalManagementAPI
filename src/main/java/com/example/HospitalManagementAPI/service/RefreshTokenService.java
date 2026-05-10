package com.example.HospitalManagementAPI.service;

import com.example.HospitalManagementAPI.entity.RefreshToken;
import com.example.HospitalManagementAPI.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository repository;

    public RefreshTokenService(RefreshTokenRepository repository) {
        this.repository = repository;
    }

    public RefreshToken createRefreshToken(String username) {

        RefreshToken token = new RefreshToken();

        token.setUsername(username);
        token.setToken(UUID.randomUUID().toString()); // secure random string
        token.setExpiryDate(
                new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)
        );

        return repository.save(token);

    }
}
