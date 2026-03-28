package com.example.HospitalManagementAPI.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter // if you have this we dont need to build getter manually
@Builder
@Setter // same thing
@AllArgsConstructor
public class PatientResponse {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String contact_number;
    private String municipality;
    private String disease;

    public PatientResponse(){}
}
