package com.clinicerp.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponseDTO {
    private Long id;
    private Long userId;
    private String fullName;
    private String specialization;
    private String phone;
}