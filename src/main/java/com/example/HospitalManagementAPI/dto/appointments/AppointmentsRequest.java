package com.example.HospitalManagementAPI.dto.appointments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class AppointmentsRequest {

    private Long patients_id;
    private Long doctors_id;
    private LocalDate appointment_date;
    private LocalTime appointment_time;
    private String status;

    public AppointmentsRequest(){}
}
