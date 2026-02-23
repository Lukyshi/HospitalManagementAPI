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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_doc() {
        return name_doc;
    }

    public void setName_doc(String name_doc) {
        this.name_doc = name_doc;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public LocalDate getSchedule() {
        return schedule;
    }

    public void setSchedule(LocalDate schedule) {
        this.schedule = schedule;
    }

    public List<appointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<appointments> appointments) {
        this.appointments = appointments;
    }

}
