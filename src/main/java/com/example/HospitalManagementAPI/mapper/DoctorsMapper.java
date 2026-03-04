package com.example.HospitalManagementAPI.mapper;

import com.example.HospitalManagementAPI.dto.doctors.DoctorsRequest;
import com.example.HospitalManagementAPI.dto.doctors.DoctorsResponse;
import com.example.HospitalManagementAPI.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorsMapper {

    public Doctor toEntity(DoctorsRequest request) {
        return Doctor.builder()
                .name_doc(request.getName_doc())
                .contact_number(request.getContact_number())
                .schedule(request.getSchedule())
                .specialization(request.getSpecialization())
                .build();
    }

    public DoctorsResponse toResponse(Doctor doctor) {
        return DoctorsResponse.builder()
                .id(doctor.getId())
                .name_doc(doctor.getName_doc())
                .contact_number(doctor.getContact_number())
                .schedule(doctor.getSchedule())
                .specialization(doctor.getSpecialization())
                .build();
    }
}
