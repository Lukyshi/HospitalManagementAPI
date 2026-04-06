package com.example.HospitalManagementAPI.controller;

import com.example.HospitalManagementAPI.dto.appointments.AppointmentsRequest;
import com.example.HospitalManagementAPI.dto.appointments.AppointmentsResponse;
import com.example.HospitalManagementAPI.service.AppointmentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentsService service;

    public AppointmentController(AppointmentsService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<AppointmentsResponse> makeAppointment(@RequestBody AppointmentsRequest request) {
        AppointmentsResponse response = service.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AppointmentsResponse>> viewAllAppointments() {
        List<AppointmentsResponse> response = service.viewAllAppointments();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/patients/{patientId}")
    @PreAuthorize("hasAnyRole('PATIENT', 'ADMIN')")
    public ResponseEntity<List<AppointmentsResponse>> viewPatientById(@PathVariable Long patientId) {
        List<AppointmentsResponse> response = service.getAppointmentByPatients(patientId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/doctors/{doctorId}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<List<AppointmentsResponse>> viewDoctorsById(@PathVariable Long doctorId) {
        List<AppointmentsResponse> response = service.getAppointmentByDoctors(doctorId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<AppointmentsResponse> updateAppointment
            (@PathVariable Long id,
             @RequestBody AppointmentsRequest request) {
        AppointmentsResponse responses = service.updateAppointment(id, request);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAppointmentById(@PathVariable Long id) {
        service.deleteAppointmentById(id);
        return ResponseEntity.noContent().build();
    }
}
