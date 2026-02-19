package com.example.HospitalManagementAPI.repository;

import com.example.HospitalManagementAPI.entity.patients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface patientsRepository extends JpaRepository<patients, Long> {

}
