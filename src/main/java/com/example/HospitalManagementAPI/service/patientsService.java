package com.example.HospitalManagementAPI.service;

import com.example.HospitalManagementAPI.dto.patient.patientRequest;
import com.example.HospitalManagementAPI.dto.patient.patientResponse;
import com.example.HospitalManagementAPI.entity.patients;
import com.example.HospitalManagementAPI.exception.ResourceNotFoundException;
import com.example.HospitalManagementAPI.repository.patientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class patientsService {
    private final patientsRepository repository;

    // avoid repeating conversion logic
    // ill create private mapping

    public patientsService(patientsRepository repository) {
        this.repository = repository;
    }

    public patientResponse createPatient(patientRequest request) {
        // create patients dto to entity
        // later i used builder pattern for cleaner
        patients entity = new patients();
        entity.setName(request.getName());
        entity.setGender(request.getGender());
        entity.setAge(request.getAge());
        entity.setPatients_contact_number(request.getContact_number());
        entity.setMunicipality(request.getMunicipality());
        entity.setDisease(request.getDisease());

        //save entity using repo
        patients savedPatient = repository.save(entity);

        // convert entity into response
        // later i create a private class for response
        patientResponse response = new patientResponse();
        response.setId(savedPatient.getId());
        response.setName(savedPatient.getName());
        response.setGender(savedPatient.getGender());
        response.setAge(savedPatient.getAge());
        response.setContact_number(savedPatient.getPatients_contact_number());
        response.setMunicipality(savedPatient.getMunicipality());
        response.setDisease(savedPatient.getDisease());

        return response;
    }

    public List<patientResponse> viewPatientsInfo() {
        List<patients> patientsList = repository.findAll();

        //
        return patientsList.stream().map(patients -> {
            patientResponse  response = new patientResponse();
            response.setId(patients.getId());
            response.setName(patients.getName());
            response.setGender(patients.getGender());
            response.setAge(patients.getAge());
            response.setContact_number(patients.getPatients_contact_number());
            response.setMunicipality(patients.getMunicipality());
            response.setDisease(patients.getDisease());
            return response;
        }).toList();
    }

    public patientResponse updatePatientsById(Long id, patientRequest request) {

        // find patients by id
        patients existingPatient = repository.findById(id)
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
        patients updatePatients = repository.save(existingPatient);

        // convert entity into response
        // later i create a private class for response
        patientResponse response = new patientResponse();
        response.setName(updatePatients.getName());
        response.setGender(updatePatients.getGender());
        response.setAge(updatePatients.getAge());
        response.setContact_number(updatePatients.getPatients_contact_number());
        response.setMunicipality(updatePatients.getMunicipality());
        response.setDisease(updatePatients.getDisease());

        return response;
    }

    public void deletePatientsById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Patients Not Found By ID" + id);
        }
        repository.deleteById(id);
    }


}
