package com.example.HospitalManagementAPI.exception;

public class ResourceNotFoundException extends RuntimeException {
    // custom runtime exception for business errors
    public ResourceNotFoundException(String message) {
        super(message);
    }
}