package com.example.HospitalManagementAPI.controller;

import com.example.HospitalManagementAPI.dto.appointments.appointmentsRequest;
import com.example.HospitalManagementAPI.dto.appointments.appointmentsResponse;
import com.example.HospitalManagementAPI.service.appointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final appointmentsService service;

    public AppointmentController(appointmentsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<appointmentsResponse> makeAppointment(@RequestBody appointmentsRequest request) {
        appointmentsResponse response = service.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<appointmentsResponse>> viewAllAppointments() {
        List<appointmentsResponse> response = service.viewAllAppointments();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/appointments/{patientId}")
    public ResponseEntity<List<appointmentsResponse>> viewPatientById(@PathVariable Long patientId) {
        List<appointmentsResponse> response = service.getAppointmentByPatients(patientId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/appointments/{doctorId}")
    public ResponseEntity<List<appointmentsResponse>> viewDoctorsById(@PathVariable Long doctorId) {
        List<appointmentsResponse> response = service.getAppointmentByDoctors(doctorId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<appointmentsResponse> updateAppointment
            (@PathVariable Long id,
             @RequestBody appointmentsRequest request) {
        appointmentsResponse responses = service.updateAppointment(id, request);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAppointmentById(@PathVariable Long id) {
        service.deleteAppointmentById(id);
        return ResponseEntity.noContent().build();
    }
}
