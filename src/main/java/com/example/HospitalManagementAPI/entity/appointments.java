package com.example.HospitalManagementAPI.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patients_id")
    private patients patients;

    @ManyToOne
    @JoinColumn(name = "doctors_id")
    private doctors doctors;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;

    public appointments(){}

    public appointments(LocalDate appointmentDate, LocalTime appointmentTime, String status) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public patients getPatients() {
        return patients;
    }

    public void setPatients(patients patients) {
        this.patients = patients;
    }

    public doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(doctors doctors) {
        this.doctors = doctors;
    }

    public LocalDate getAppointment_date() {
        return appointmentDate;
    }

    public void setAppointment_date(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointment_time() {
        return appointmentTime;
    }

    public void setAppointment_time(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
