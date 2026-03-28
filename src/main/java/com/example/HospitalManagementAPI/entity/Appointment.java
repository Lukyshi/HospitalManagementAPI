package com.example.HospitalManagementAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patients_id")
    private Patient patients;

    @ManyToOne
    @JoinColumn(name = "doctors_id")
    private Doctor doctors;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;

    public Appointment(){}

    public Appointment(LocalDate appointmentDate, LocalTime appointmentTime, String status) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

}
