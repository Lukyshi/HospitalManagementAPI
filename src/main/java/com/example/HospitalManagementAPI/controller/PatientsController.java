package com.example.HospitalManagementAPI.controller;

import com.example.HospitalManagementAPI.dto.patient.PatientsRequest;
import com.example.HospitalManagementAPI.dto.patient.PatientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.HospitalManagementAPI.service.PatientsService;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    private final PatientsService service;

    public PatientsController(PatientsService service) {
        this.service = service;
    }

    // create patients
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    // response entity controll the full http status
    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientsRequest request) {
        PatientResponse response = service.createPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        List<PatientResponse> patientResponse = service.viewPatientsInfo();
        return ResponseEntity.ok(patientResponse);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientResponse> updatePatientById
            (@PathVariable Long id,
             @RequestBody PatientsRequest request) {
        PatientResponse response = service.updatePatientsById(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePatients(@PathVariable Long id) {
        service.deletePatientsById(id);

        return ResponseEntity.noContent().build(); // return status
    }
}
