package com.example.HospitalManagementAPI.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String role;
}
