package com.example.HospitalManagementAPI.repository;

import com.example.HospitalManagementAPI.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepository extends JpaRepository<Patient, Long> {

}
