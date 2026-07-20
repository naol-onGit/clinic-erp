package com.clinicerp.backend.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDTO {

    @NotBlank(message = "Full name is required")
    private String fullName;

    private LocalDate dob;

    @NotBlank(message = "Sex is required")
    private String sex;

    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    private String bloodGroup;
}