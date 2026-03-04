package com.example.HospitalManagementAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name_doc;
    private String specialization;
    private String contact_number;
    private LocalDate schedule;

    @OneToMany(mappedBy = "doctors")
    private List<Appointment> appointments;

    public Doctor() {}

    public Doctor(String name_doc, String specialization,
                  String contact_number, LocalDate schedule) {
        this.name_doc = name_doc;
        this.specialization = specialization;
        this.contact_number = contact_number;
        this.schedule = schedule;
    }
}
