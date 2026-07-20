package com.clinicerp.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDatetime;
    private String status;
    private String notes;
    private LocalDateTime createdAt;
}