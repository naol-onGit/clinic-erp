package com.clinicerp.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDTO {
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDatetime;
    private String status;
    private String notes;
}