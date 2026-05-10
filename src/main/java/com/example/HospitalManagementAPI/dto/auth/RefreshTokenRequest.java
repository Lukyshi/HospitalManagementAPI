package com.example.HospitalManagementAPI.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RefreshTokenRequest {

    private String refreshToken;

}
