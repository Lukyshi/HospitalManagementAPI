package com.example.HospitalManagementAPI.service;

import com.example.HospitalManagementAPI.dto.doctors.doctorsRequest;
import com.example.HospitalManagementAPI.dto.doctors.doctorsResponse;
import com.example.HospitalManagementAPI.dto.patient.patientResponse;
import com.example.HospitalManagementAPI.entity.doctors;
import com.example.HospitalManagementAPI.repository.doctorsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class doctorsService {

    private final doctorsRepository repository;

    public doctorsService(doctorsRepository repository) {
        this.repository = repository;
    }

    public doctorsResponse createDoctor(doctorsRequest request) {
        doctors entity = new doctors();
        entity.setContact_number(request.getContact_number());
        entity.setName_doc(request.getName_doc());
        entity.setSchedule(request.getSchedule());
        entity.setSpecialization(request.getSpecialization());

        doctors doctorSaved = repository.save(entity);

        doctorsResponse response = new doctorsResponse();
        response.setId(doctorSaved.getId());
        response.setContact_number(doctorSaved.getContact_number());
        response.setName_doc(doctorSaved.getName_doc());
        response.setSchedule(doctorSaved.getSchedule());
        response.setSpecialization(doctorSaved.getSpecialization());

        return response;
    }

    public List<doctorsResponse> viewAllDoctors() {
        List<doctors> doctorsList = repository.findAll();

        return doctorsList.stream().map(doctors -> {
            doctorsResponse response = new doctorsResponse();
            response.setId(doctors.getId());
            response.setContact_number(doctors.getContact_number());
            response.setName_doc(doctors.getName_doc());
            response.setSchedule(doctors.getSchedule());
            response.setSpecialization(doctors.getSpecialization());
            return response;
        }).toList();
    }


    public doctorsResponse updateDoctor(Long id, doctorsRequest request) {
        doctors existingDoctors = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctors Not Found By ID" + id));

        if(request.getName_doc() != null) {
            existingDoctors.setName_doc(request.getName_doc());
        }
        if(request.getContact_number() != null) {
            existingDoctors.setContact_number(request.getContact_number());
        }
        if(request.getSchedule() != null) {
            existingDoctors.setSchedule(request.getSchedule());
        }
        if(request.getSpecialization() != null) {
            existingDoctors.setSpecialization(request.getSpecialization());
        }

        doctors saved = repository.save(existingDoctors);

        doctorsResponse response = new doctorsResponse();
        response.setId(saved.getId());
        response.setContact_number(saved.getContact_number());
        response.setName_doc(saved.getName_doc());
        response.setSchedule(saved.getSchedule());
        response.setSpecialization(saved.getSpecialization());
        return response;
    }

    public void deleteDoctorsById(Long id) {
        if(!repository.existsById(id)) {
            throw new RuntimeException("Doctors Not Found" + id);
        }

        repository.deleteById(id);
    }

}
