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

}
