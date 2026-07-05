package com.clinicerp.backend.mapper;

import com.clinicerp.backend.dto.DoctorRequestDTO;
import com.clinicerp.backend.dto.DoctorResponseDTO;
import com.clinicerp.backend.entity.Doctor;

public class DoctorMapper {

    public static Doctor toEntity(DoctorRequestDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setUserId(dto.getUserId());
        doctor.setFullName(dto.getFullName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setPhone(dto.getPhone());
        return doctor;
    }

    public static DoctorResponseDTO toResponseDTO(Doctor doctor) {
        DoctorResponseDTO dto = new DoctorResponseDTO();
        dto.setId(doctor.getId());
        dto.setUserId(doctor.getUserId());
        dto.setFullName(doctor.getFullName());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setPhone(doctor.getPhone());
        return dto;
    }
}