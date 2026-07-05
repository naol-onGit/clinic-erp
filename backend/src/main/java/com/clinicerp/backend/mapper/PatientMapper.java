package com.clinicerp.backend.mapper;

import com.clinicerp.backend.dto.PatientRequestDTO;
import com.clinicerp.backend.dto.PatientResponseDTO;
import com.clinicerp.backend.entity.Patient;

public class PatientMapper {

    public static Patient toEntity(PatientRequestDTO dto) {
        // build and return a new Patient from dto's fields
        Patient patient = new Patient();
        patient.setFullName(dto.getFullName());
        patient.setDob(dto.getDob());
        patient.setSex(dto.getSex());
        patient.setPhone(dto.getPhone());
        patient.setAddress(dto.getAddress());
        patient.setBloodGroup(dto.getBloodGroup());
        return patient;
    }

    public static PatientResponseDTO toResponseDTO(Patient patient) {
        // build and return a new PatientResponseDTO from patient's fields
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId());
        dto.setFullName(patient.getFullName());
        dto.setDob(patient.getDob());
        dto.setSex(patient.getSex());
        dto.setPhone(patient.getPhone());
        dto.setAddress(patient.getAddress());
        dto.setBloodGroup(patient.getBloodGroup());
        dto.setCreatedAt(patient.getCreatedAt());
        return dto;
    }
}

