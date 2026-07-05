package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.Appointment;
import com.clinicerp.backend.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Test
    void createAppointment_shouldSaveAndReturnAppointment() {
        Appointment appointment = new Appointment();
        appointment.setStatus("SCHEDULED");
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        Appointment result = appointmentService.createAppointment(appointment);

        assertEquals("SCHEDULED", result.getStatus());
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    void getAppointmentById_shouldReturnAppointment_whenExists() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        assertTrue(appointmentService.getAppointmentById(1L).isPresent());
    }

    @Test
    void getAppointmentById_shouldReturnEmpty_whenNotFound() {
        when(appointmentRepository.findById(999L)).thenReturn(Optional.empty());

        assertTrue(appointmentService.getAppointmentById(999L).isEmpty());
    }

    @Test
    void getAllAppointments_shouldReturnList() {
        when(appointmentRepository.findAll()).thenReturn(List.of(new Appointment(), new Appointment()));

        assertEquals(2, appointmentService.getAllAppointments().size());
    }

    @Test
    void deleteAppointment_shouldCallRepositoryDelete() {
        appointmentService.deleteAppointment(1L);
        verify(appointmentRepository, times(1)).deleteById(1L);
    }
}