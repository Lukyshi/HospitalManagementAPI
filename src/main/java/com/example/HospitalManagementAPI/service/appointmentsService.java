package com.example.HospitalManagementAPI.service;

import com.example.HospitalManagementAPI.dto.appointments.appointmentsRequest;
import com.example.HospitalManagementAPI.dto.appointments.appointmentsResponse;
import com.example.HospitalManagementAPI.entity.appointments;
import com.example.HospitalManagementAPI.entity.doctors;
import com.example.HospitalManagementAPI.entity.patients;
import com.example.HospitalManagementAPI.exception.ConflictException;
import com.example.HospitalManagementAPI.exception.ResourceNotFoundException;
import com.example.HospitalManagementAPI.repository.appointmentsRepository;
import com.example.HospitalManagementAPI.repository.doctorsRepository;
import com.example.HospitalManagementAPI.repository.patientsRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class appointmentsService {

    private final appointmentsRepository appointmentsRepository;
    private final patientsRepository patientsRepository;
    private final doctorsRepository doctorsRepository;


    public appointmentsService(appointmentsRepository appointmentsRepository,
                               patientsRepository patientsRepository,
                               doctorsRepository doctorsRepository) {
        this.appointmentsRepository = appointmentsRepository;
        this.patientsRepository = patientsRepository;
        this.doctorsRepository = doctorsRepository;

    }

    public appointmentsResponse createAppointment(appointmentsRequest request) {

        // find if patients id and doctors id are exist
        patients patient = patientsRepository.findById(request.getPatients_id())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        doctors doctor = doctorsRepository.findById(request.getDoctors_id())
                .orElseThrow(() -> new RuntimeException("Doctors not found"));

        //find if doctors already booked
        boolean exist = appointmentsRepository.existsByDoctorsAndAppointmentDateAndAppointmentTime(
                doctor,
                request.getAppointment_date(),
                request.getAppointment_time()
        );

        if(exist) {
            throw new ConflictException("Doctor already has an appointment at this time.");
        }

        appointments entity = new appointments();
        entity.setPatients(patient);
        entity.setDoctors(doctor);
        entity.setAppointment_date(request.getAppointment_date());
        entity.setAppointment_time(request.getAppointment_time());
        entity.setStatus(request.getStatus());

        appointments saved = appointmentsRepository.save(entity);

        // create a method for response
        return mapToResponse(saved);
    }

    private appointmentsResponse mapToResponse(appointments appointments) {
        appointmentsResponse response = new appointmentsResponse();
        response.setId(appointments.getId());
        response.setPatients_id(appointments.getPatients().getId());
        response.setDoctors_id(appointments.getDoctors().getId());
        response.setAppointment_date(appointments.getAppointment_date());
        response.setAppointment_time(appointments.getAppointment_time());
        response.setStatus(appointments.getStatus());
        return response;
    }


    public List<appointmentsResponse> viewAllAppointments() {
        List<appointments> appointmentsList = appointmentsRepository.findAll();

        return appointmentsList.stream().map(appointments -> {
            appointmentsResponse response = new appointmentsResponse();
            response.setId(appointments.getId());
            response.setPatients_id(appointments.getPatients().getId());
            response.setDoctors_id(appointments.getDoctors().getId());
            response.setAppointment_date(appointments.getAppointment_date());
            response.setAppointment_time(appointments.getAppointment_time());
            response.setStatus(appointments.getStatus());
            return response;
        }).toList();
    }

    public List<appointmentsResponse> getAppointmentByPatients(Long patientId) {
        List<appointments> appointmentsList = appointmentsRepository.findByPatients_Id(patientId);
        return appointmentsList.stream()
                .map(this::mapToResponse) // appointments -> mapToResponse(appointment)
                .collect(Collectors.toList());
    }

    public List<appointmentsResponse> getAppointmentByDoctors(Long doctorsId) {
        List<appointments> appointmentsList = appointmentsRepository.findByDoctors_Id(doctorsId);
        return appointmentsList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public appointmentsResponse updateAppointment(Long id, appointmentsRequest request) {
        appointments appointments = appointmentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointments Not Found By ID" +id));

        // fix this repeating later
        doctors doctor = doctorsRepository.findById(request.getDoctors_id())
                .orElseThrow(() -> new ResourceNotFoundException("Doctors not found"));

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

        appointments saved = appointmentsRepository.save(appointments);

        // return response
        return mapToResponse(saved);

    }

    public void deleteAppointmentById(Long id) {
        if(!appointmentsRepository.existsById(id)) {
            throw new RuntimeException("Appointments Not Found" + id);
        }

        appointmentsRepository.deleteById(id);
    }

}
