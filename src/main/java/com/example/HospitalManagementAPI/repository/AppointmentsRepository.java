package com.example.HospitalManagementAPI.repository;

import com.example.HospitalManagementAPI.entity.Appointment;
import com.example.HospitalManagementAPI.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorsAndAppointmentDateAndAppointmentTime
            (Doctor doctors, LocalDate appointmentDate, LocalTime appointmentTime);

    boolean existsByDoctorsAndAppointmentDateAndAppointmentTimeAndIdNot
            (Doctor doctors, LocalDate appointmentDate, LocalTime appointmentTime, Long id);

    List<Appointment> findByPatients_Id(Long patientId);

    List<Appointment> findByDoctors_Id(Long doctorsId);
}