package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    Optional<Appointment> getAppointmentById(Long id);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
}