package com.example.HospitalManagementAPI.service;

import com.example.HospitalManagementAPI.dto.appointments.AppointmentsRequest;
import com.example.HospitalManagementAPI.dto.appointments.AppointmentsResponse;
import com.example.HospitalManagementAPI.entity.Appointment;
import com.example.HospitalManagementAPI.entity.Doctor;
import com.example.HospitalManagementAPI.entity.Patient;
import com.example.HospitalManagementAPI.exception.ConflictException;
import com.example.HospitalManagementAPI.exception.ResourceNotFoundException;
import com.example.HospitalManagementAPI.mapper.AppointmentsMapper;
import com.example.HospitalManagementAPI.repository.AppointmentsRepository;
import com.example.HospitalManagementAPI.repository.DoctorsRepository;
import com.example.HospitalManagementAPI.repository.PatientsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentsService {

    private final AppointmentsRepository appointmentsRepository;
    private final PatientsRepository patientsRepository;
    private final DoctorsRepository doctorsRepository;
    private final AppointmentsMapper appointmentMapper;

    public AppointmentsService(AppointmentsRepository appointmentsRepository,
                               PatientsRepository patientsRepository,
                               DoctorsRepository doctorsRepository,
                               AppointmentsMapper appointmentMapper) {
        this.appointmentsRepository = appointmentsRepository;
        this.patientsRepository = patientsRepository;
        this.doctorsRepository = doctorsRepository;
        this.appointmentMapper = appointmentMapper;

    }

    private void checkIfDoctorAlreadyBooked(Doctor doctor, LocalDate appointmentDate,
                                            LocalTime appointmentTime) {
        boolean alreadyBooked = appointmentsRepository.existsByDoctorsAndAppointmentDateAndAppointmentTime
                (doctor, appointmentDate, appointmentTime);
        if(alreadyBooked) {
            throw new ConflictException("Doctor already has an appointment at this time.");
        }
    }

    private Patient checkIfPatientExist(Long patientId) {
        return patientsRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"));
    }

    private Doctor checkIfDoctorExist(Long doctorId) {
        return doctorsRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
    }

    public AppointmentsResponse createAppointment(AppointmentsRequest request) {
        // find if patients id and doctors id are exist
        //find if doctors already booked
        Patient patient = checkIfPatientExist(request.getPatients_id());
        Doctor doctor = checkIfDoctorExist(request.getDoctors_id());
        checkIfDoctorAlreadyBooked(doctor, request.getAppointment_date(), request.getAppointment_time());
        Appointment saved = appointmentsRepository.save(appointmentMapper.toEntity(request, patient, doctor));
        return appointmentMapper.toResponse(saved);
    }

    public List<AppointmentsResponse> viewAllAppointments() {
        return appointmentsRepository.findAll()
                .stream()
                //.map(this::mapToResponse)// i chang this because i seperate the mapper
                // appointments -> mapToResponse(appointment)
                .map(appointmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AppointmentsResponse> getAppointmentByPatients(Long patientId) {
        List<Appointment> appointmentsList = appointmentsRepository.findByPatients_Id(patientId);
        return appointmentsList.stream()
                .map(appointmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AppointmentsResponse> getAppointmentByDoctors(Long doctorsId) {
        List<Appointment> appointmentsList = appointmentsRepository.findByDoctors_Id(doctorsId);
        return appointmentsList.stream()
                .map(appointmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public AppointmentsResponse updateAppointment(Long id, AppointmentsRequest request) {

        Appointment appointments = appointmentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointments Not Found By ID" +id));

        Doctor doctor = checkIfDoctorExist(request.getDoctors_id());

        if(request.getDoctors_id() != null) {
            appointments.setDoctors(doctor);
        }

        if(request.getAppointment_date() != null) {
            appointments.setAppointment_date(request.getAppointment_date());
        }

        if(request.getAppointment_time() != null) {
            appointments.setAppointment_time(request.getAppointment_time());
        }

        if(request.getDoctors_id() != null
        || request.getAppointment_date() != null
        || request.getAppointment_time() != null) {

            boolean exist = appointmentsRepository.existsByDoctorsAndAppointmentDateAndAppointmentTimeAndIdNot(
                    appointments.getDoctors(),
                    appointments.getAppointment_date(),
                    appointments.getAppointment_time(),
                    appointments.getId()
            );
            if(exist) {
                throw new ConflictException("Doctor already has an appointment at this time.");
            }
        }

        if(request.getStatus() != null) {
            appointments.setStatus(request.getStatus());
        }

        Appointment saved = appointmentsRepository.save(appointments);
        // return response
        return appointmentMapper.toResponse(saved);

    }

    public void deleteAppointmentById(Long id) {
        if(!appointmentsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointments Not Found" + id);
        }

        appointmentsRepository.deleteById(id);
    }

}
