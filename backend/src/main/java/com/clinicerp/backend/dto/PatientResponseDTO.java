package com.clinicerp.backend.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatientResponseDTO {

    private Long id;
    private String fullName;
    private LocalDate dob;
    private String sex;
    private String phone;
    private String address;
    private String bloodGroup;
    private LocalDateTime createdAt;
}