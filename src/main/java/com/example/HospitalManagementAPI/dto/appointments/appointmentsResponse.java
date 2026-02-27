package com.example.HospitalManagementAPI.dto.appointments;

import java.time.LocalDate;
import java.time.LocalTime;

public class appointmentsResponse {

    private Long id;
    private Long patients_id;
    private Long doctors_id;
    private LocalDate appointment_date;
    private LocalTime appointment_time;
    private String status;

    public appointmentsResponse(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatients_id() {
        return patients_id;
    }

    public void setPatients_id(Long patients_id) {
        this.patients_id = patients_id;
    }

    public Long getDoctors_id() {
        return doctors_id;
    }

    public void setDoctors_id(Long doctors_id) {
        this.doctors_id = doctors_id;
    }

    public LocalDate getAppointment_date(){
        return appointment_date;
    }

    public void setAppointment_date(LocalDate appointment_date){
        this.appointment_date = appointment_date;
    }

    public LocalTime getAppointment_time(){
        return appointment_time;
    }

    public void setAppointment_time(LocalTime appointment_time){
        this.appointment_time = appointment_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
