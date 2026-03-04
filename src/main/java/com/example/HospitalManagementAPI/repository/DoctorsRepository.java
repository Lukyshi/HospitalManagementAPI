package com.example.HospitalManagementAPI.repository;

import com.example.HospitalManagementAPI.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsRepository extends JpaRepository<Doctor,Long> {
}
