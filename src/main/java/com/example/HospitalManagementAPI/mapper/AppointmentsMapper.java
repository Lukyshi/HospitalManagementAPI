package com.example.HospitalManagementAPI.mapper;

import com.example.HospitalManagementAPI.dto.appointments.AppointmentsRequest;
import com.example.HospitalManagementAPI.dto.appointments.AppointmentsResponse;
import com.example.HospitalManagementAPI.entity.Appointment;
import com.example.HospitalManagementAPI.entity.Doctor;
import com.example.HospitalManagementAPI.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class AppointmentsMapper {

    public Appointment toEntity(AppointmentsRequest request, Patient patient, Doctor doctor) {
        return Appointment.builder()
                .patients(patient)
                .doctors(doctor)
                .appointmentDate(request.getAppointment_date())
                .appointmentTime(request.getAppointment_time())
                .status(request.getStatus())
                .build();
    }

    public AppointmentsResponse toResponse(Appointment appointments) {
        return AppointmentsResponse.builder()
                .id(appointments.getId())
                .patients_id(appointments.getPatients().getId())
                .doctors_id(appointments.getDoctors ().getId())
                .appointment_date(appointments.getAppointment_date())
                .appointment_time(appointments.getAppointment_time())
                .status(appointments.getStatus())
                .build();
    }
}
