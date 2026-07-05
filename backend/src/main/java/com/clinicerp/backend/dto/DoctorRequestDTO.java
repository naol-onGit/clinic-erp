package com.clinicerp.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDTO {
    private Long userId;
    private String fullName;
    private String specialization;
    private String phone;
}