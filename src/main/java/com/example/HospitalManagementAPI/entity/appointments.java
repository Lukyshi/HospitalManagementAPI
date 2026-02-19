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

    private LocalDate appointment_date;
    private LocalTime appointment_time;
    private String status;

    public appointments(){}

    public appointments(LocalDate appointment_date, LocalTime appointment_time, String status) {
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public patients getPatients() {
        return patients;
    }

    public doctors getDoctors() {
        return doctors;
    }

    public LocalDate getAppointment_date() {
        return appointment_date;
    }

    public LocalTime getAppointment_time() {
        return appointment_time;
    }

    public String getStatus() {
        return status;
    }
}
