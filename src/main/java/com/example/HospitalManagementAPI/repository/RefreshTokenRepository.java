package com.example.HospitalManagementAPI.repository;

import com.example.HospitalManagementAPI.dto.auth.RefreshTokenRequest;
import com.example.HospitalManagementAPI.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    //delete token when user logs out
    void deleteById(String token);
}
