package com.example.HospitalManagementAPI.controller;

import com.example.HospitalManagementAPI.dto.patient.patientRequest;
import com.example.HospitalManagementAPI.dto.patient.patientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.HospitalManagementAPI.service.patientsService;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    private final patientsService service;

    public PatientsController(patientsService service) {
        this.service = service;
    }

    // create patients
    @PostMapping
    // response entity controll the full http status
    public ResponseEntity<patientResponse> createPatient(@RequestBody patientRequest request) {
        patientResponse response = service.createPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<patientResponse>> getAllPatients() {
        List<patientResponse> patientResponse = service.viewPatientsInfo();
        return ResponseEntity.ok(patientResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<patientResponse> updatePatientById
            (@PathVariable Long id,
             @RequestBody patientRequest request) {
        patientResponse response = service.updatePatientsById(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatients(@PathVariable Long id) {
        service.deletePatientsById(id);

        return ResponseEntity.noContent().build(); // return status
    }
}
