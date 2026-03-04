package com.example.HospitalManagementAPI.service;

import com.example.HospitalManagementAPI.dto.doctors.DoctorsRequest;
import com.example.HospitalManagementAPI.dto.doctors.DoctorsResponse;
import com.example.HospitalManagementAPI.entity.Doctor;
import com.example.HospitalManagementAPI.mapper.DoctorsMapper;
import com.example.HospitalManagementAPI.repository.DoctorsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorsService {

    private final DoctorsMapper mapper;
    private final DoctorsRepository repository;

    public DoctorsService(DoctorsRepository repository, DoctorsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DoctorsResponse createDoctor(DoctorsRequest request) {
        Doctor doctorSaved = repository.save(mapper.toEntity(request));
        return mapper.toResponse(doctorSaved);
    }

    public List<DoctorsResponse> viewAllDoctors() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse) // map(patients -> mapToResponse(patients))
                .collect(Collectors.toList());
    }

    public DoctorsResponse updateDoctor(Long id, DoctorsRequest request) {
        Doctor existingDoctors = repository.findById(id)
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

        Doctor saved = repository.save(existingDoctors);
        return mapper.toResponse(saved);
    }

    public void deleteDoctorsById(Long id) {
        if(!repository.existsById(id)) {
            throw new RuntimeException("Doctors Not Found" + id);
        }
        repository.deleteById(id);
    }

}
