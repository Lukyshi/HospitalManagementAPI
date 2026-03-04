package com.example.HospitalManagementAPI.dto.doctors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter // if you have this we dont need to build getter manually
@Builder
@Setter // same thing
@AllArgsConstructor
public class DoctorsRequest {

    private String name_doc;
    private String specialization;
    private String contact_number;
    private LocalDate schedule;

    public DoctorsRequest(){}

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

}
