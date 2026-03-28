package com.example.HospitalManagementAPI.dto.auth;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoginRequest {
    private String username;
    private String password;
}
