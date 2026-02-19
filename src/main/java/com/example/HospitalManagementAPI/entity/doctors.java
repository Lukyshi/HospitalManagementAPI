package com.example.HospitalManagementAPI.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "doctors")
public class doctors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name_doc;
    private String specialization;
    private String contact_number;
    private LocalDate schedule;

    @OneToMany(mappedBy = "doctors")
    private List<appointments> appointments;

    public doctors() {}

    public doctors(String name_doc, String specialization,
                   String contact_number, LocalDate schedule) {
        this.name_doc = name_doc;
        this.specialization = specialization;
        this.contact_number = contact_number;
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public String getName_doc() {
        return name_doc;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getContact_number() {
        return contact_number;
    }

    public LocalDate getSchedule() {
        return schedule;
    }

}
