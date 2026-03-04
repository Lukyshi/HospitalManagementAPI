package com.example.HospitalManagementAPI.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter // if you have this we dont need to build getter manually
@Builder
@Setter // same thing
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String gender;
    private Integer age;
    private String patients_contact_number;
    private String municipality;
    private String disease;

    @OneToMany(mappedBy = "patients")
    private List<Appointment>appointments;

    public Patient(){}

    public Patient(String name, String gender, Integer age, String patients_contact_number,
                   String municipality, String disease) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.patients_contact_number = patients_contact_number;
        this.municipality = municipality;
        this.disease = disease;
    }
}
