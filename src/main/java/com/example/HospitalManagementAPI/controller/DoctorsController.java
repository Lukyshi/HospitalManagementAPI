package com.example.HospitalManagementAPI.controller;

import com.example.HospitalManagementAPI.dto.doctors.DoctorsRequest;
import com.example.HospitalManagementAPI.dto.doctors.DoctorsResponse;
import com.example.HospitalManagementAPI.service.DoctorsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/doctors")
public class DoctorsController {

    private final DoctorsService service;

    public DoctorsController(DoctorsService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorsResponse> createDoctors(@RequestBody DoctorsRequest request) {
        DoctorsResponse response = service.createDoctor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // return status 201
    }

    // list the doctors
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    public ResponseEntity<List<DoctorsResponse>> viewAllDoctors() {
        List<DoctorsResponse> responses = service.viewAllDoctors();
        return ResponseEntity.ok(responses); // return status 200
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorsResponse> response
            (@PathVariable Long id,
             @RequestBody DoctorsRequest request) {
        DoctorsResponse response = service.updateDoctor(id, request);
        return ResponseEntity.ok(response); // return status
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteDoctorsById(id);
        return ResponseEntity.noContent().build(); // return status 204
    }

}
