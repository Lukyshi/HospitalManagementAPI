package com.example.HospitalManagementAPI.repository;

import com.example.HospitalManagementAPI.entity.doctors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface doctorsRepository extends JpaRepository<doctors,Long> {
}
