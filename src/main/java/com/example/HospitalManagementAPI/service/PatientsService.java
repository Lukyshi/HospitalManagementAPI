package com.example.HospitalManagementAPI.service;

import com.example.HospitalManagementAPI.dto.patient.PatientsRequest;
import com.example.HospitalManagementAPI.dto.patient.PatientResponse;
import com.example.HospitalManagementAPI.entity.Patient;
import com.example.HospitalManagementAPI.exception.ResourceNotFoundException;
import com.example.HospitalManagementAPI.mapper.PatientsMapper;
import com.example.HospitalManagementAPI.repository.PatientsRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientsService {
    private final PatientsMapper mapper;
    private final PatientsRepository repository;

    // avoid repeating conversion logic
    // ill create private mapping

    public PatientsService(PatientsRepository repository, PatientsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PatientResponse createPatient(PatientsRequest request) {
        // create patients dto to entity
        // I used builder pattern for cleaner
       // call map entity
        //save entity using repo
        Patient patientSaved = repository.save(mapper.toEntity(request));
        // convert entity into response
        return mapper.toResponse(patientSaved);
    }

    public List<PatientResponse> viewPatientsInfo() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public PatientResponse updatePatientsById(Long id, PatientsRequest request) {

        // find patients by id
        Patient existingPatient = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patients Not Found By ID" + id));

        // update
        if (request.getName() != null) {
            existingPatient.setName(request.getName());
        }
        if (request.getGender() != null) {
            existingPatient.setGender(request.getGender());
        }
        if (request.getAge() != null) {
            existingPatient.setAge(request.getAge());
        }
        if (request.getContact_number() != null) {
            existingPatient.setPatients_contact_number(request.getContact_number());
        }
        if (request.getMunicipality() != null) {
            existingPatient.setMunicipality(request.getMunicipality());
        }
        if (request.getDisease() != null) {
            existingPatient.setDisease(request.getDisease());
        }

        // save
        Patient updatePatients = repository.save(existingPatient);
        // convert entity into response
        // later i create a private class for response
        return mapper.toResponse(updatePatients);
    }

    public void deletePatientsById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Patients Not Found By ID" + id);
        }
        repository.deleteById(id);
    }


}
