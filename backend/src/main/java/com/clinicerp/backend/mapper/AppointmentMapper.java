package com.clinicerp.backend.mapper;

import com.clinicerp.backend.dto.AppointmentRequestDTO;
import com.clinicerp.backend.dto.AppointmentResponseDTO;
import com.clinicerp.backend.entity.Appointment;

public class AppointmentMapper {

    public static Appointment toEntity(AppointmentRequestDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setPatientId(dto.getPatientId());
        appointment.setDoctorId(dto.getDoctorId());
        appointment.setAppointmentDatetime(dto.getAppointmentDatetime());
        appointment.setStatus(dto.getStatus());
        appointment.setNotes(dto.getNotes());
        return appointment;
    }

    public static AppointmentResponseDTO toResponseDTO(Appointment appointment) {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setId(appointment.getId());
        dto.setPatientId(appointment.getPatientId());
        dto.setDoctorId(appointment.getDoctorId());
        dto.setAppointmentDatetime(appointment.getAppointmentDatetime());
        dto.setStatus(appointment.getStatus());
        dto.setNotes(appointment.getNotes());
        dto.setCreatedAt(appointment.getCreatedAt());
        return dto;
    }
}