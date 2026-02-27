package com.example.HospitalManagementAPI.controller;

import com.example.HospitalManagementAPI.dto.doctors.doctorsRequest;
import com.example.HospitalManagementAPI.dto.doctors.doctorsResponse;
import com.example.HospitalManagementAPI.service.doctorsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/doctors")
public class DoctorsController {

    private final doctorsService service;

    public DoctorsController(doctorsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<doctorsResponse> createDoctors(@RequestBody doctorsRequest request) {
        doctorsResponse response = service.createDoctor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // return status 201
    }

    // list the doctors
    @GetMapping
    public ResponseEntity<List<doctorsResponse>> viewAllDoctors() {
        List<doctorsResponse> responses = service.viewAllDoctors();
        return ResponseEntity.ok(responses); // return status 200
    }

    @PatchMapping("{id}")
    public ResponseEntity<doctorsResponse>  response
            (@PathVariable Long id,
             @RequestBody doctorsRequest request) {
        doctorsResponse response = service.updateDoctor(id, request);
        return ResponseEntity.ok(response); // return status
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteDoctorsById(id);
        return ResponseEntity.noContent().build(); // return status 204
    }

}
