package com.example.HospitalManagementAPI.entity;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "patients")
public class patients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String gender;
    private int age;
    private String patients_contact_number;
    private String municipality;
    private String disease;

    @OneToMany(mappedBy = "patients")
    private List<appointments>appointments;

    public patients(){}

    public patients(String name, String gender, int age, String patients_contact_number,
                    String municipality, String disease) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.patients_contact_number = patients_contact_number;
        this.municipality = municipality;
        this.disease = disease;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPatients_contact_number() {
        return patients_contact_number;
    }

    public void setPatients_contact_number(String patients_contact_number) {
        this.patients_contact_number = patients_contact_number;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public List<appointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<appointments> appointments) {
        this.appointments = appointments;
    }
}
