package com.example.HospitalManagementAPI.service;

import com.example.HospitalManagementAPI.dto.patient.patientRequest;
import com.example.HospitalManagementAPI.dto.patient.patientResponse;
import com.example.HospitalManagementAPI.entity.patients;
import com.example.HospitalManagementAPI.repository.patientsRepository;
import org.springframework.stereotype.Service;

@Service
public class patientsService {
    private patientsRepository repository;

    public patientsService(patientsRepository repository) {
        this.repository = repository;
    }

    public patientResponse createPatient(patientRequest request) {
        // create patients dto to entity
        // later i used builder pattern for cleaner
        patients patientsEntity = new patients();
        patientsEntity.setName(request.getName());
        patientsEntity.setGender(request.getGender());
        patientsEntity.setAge(request.getAge());
        patientsEntity.setPatients_contact_number(request.getContact_number());
        patientsEntity.setMunicipality(request.getMunicipality());
        patientsEntity.setDisease(request.getDisease());

        //save entity using repo
        patients savedPatient = repository.save(patientsEntity);

        // convert entity into response
        // later i create a private class for response
        patientResponse response = new patientResponse();
        response.setName(savedPatient.getName());
        response.setGender(savedPatient.getGender());
        response.setAge(savedPatient.getAge());
        response.setContact_number(savedPatient.getPatients_contact_number());
        response.setMunicipality(savedPatient.getMunicipality());
        response.setDisease(savedPatient.getDisease());

        return response;
    }

}
