package com.clinicerp.backend.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatientRequestDTO {
    private String fullName;
    private LocalDate dob;
    private String sex;
    private String phone;
    private String address;
    private String bloodGroup;
}