package com.example.HospitalManagementAPI.entity;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "com/example/HospitalManagementAPI/dto")
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

    @OneToMany(mappedBy = "com/example/HospitalManagementAPI/dto")
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

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getPatients_contact_number() {
        return patients_contact_number;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getDisease() {
        return disease;
    }

}
