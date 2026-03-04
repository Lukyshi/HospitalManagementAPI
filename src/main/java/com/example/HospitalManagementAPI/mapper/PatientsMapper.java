package com.example.HospitalManagementAPI.mapper;

import com.example.HospitalManagementAPI.dto.patient.PatientResponse;
import com.example.HospitalManagementAPI.dto.patient.PatientsRequest;
import com.example.HospitalManagementAPI.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientsMapper {

    public Patient toEntity(PatientsRequest request) {
        // use builder for cleaner version
        return Patient.builder()
                .name(request.getName())
                .gender(request.getGender())
                .age(request.getAge())
                .patients_contact_number(request.getContact_number())
                .municipality(request.getMunicipality())
                .disease(request.getDisease())
                .build();
    }

    public PatientResponse toResponse(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .name(patient.getName())
                .gender(patient.getGender())
                .municipality(patient.getMunicipality())
                .age(patient.getAge())
                .contact_number(patient.getPatients_contact_number())
                .disease(patient.getDisease())
                .build();
    }
}
