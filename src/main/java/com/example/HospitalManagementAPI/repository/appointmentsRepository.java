package com.example.HospitalManagementAPI.repository;

import com.example.HospitalManagementAPI.entity.appointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface appointmentsRepository extends JpaRepository<appointments, Long> {
}
