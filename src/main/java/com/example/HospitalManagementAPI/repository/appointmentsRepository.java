package com.example.HospitalManagementAPI.repository;

import com.example.HospitalManagementAPI.entity.appointments;
import com.example.HospitalManagementAPI.entity.doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface appointmentsRepository extends JpaRepository<appointments, Long> {
    boolean existsByDoctorsAndAppointmentDateAndAppointmentTime
            (doctors doctors, LocalDate appointmentDate, LocalTime appointmentTime);
    boolean existsByDoctorsAndAppointmentDateAndAppointmentTimeAndIdNot
            (doctors doctors, LocalDate appointmentDate, LocalTime appointmentTime, Long id);
    List<appointments> findByPatients_Id(Long patientId);
    List<appointments> findByDoctors_Id(Long doctorsId);
}