package com.clinicerp.backend.controller;

import com.clinicerp.backend.dto.AppointmentRequestDTO;
import com.clinicerp.backend.dto.AppointmentResponseDTO;
import com.clinicerp.backend.entity.Appointment;
import com.clinicerp.backend.mapper.AppointmentMapper;
import com.clinicerp.backend.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@RequestBody AppointmentRequestDTO requestDTO) {
        Appointment appointment = AppointmentMapper.toEntity(requestDTO);
        Appointment saved = appointmentService.createAppointment(appointment);
        return ResponseEntity.status(201).body(AppointmentMapper.toResponseDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        List<AppointmentResponseDTO> result = appointmentService.getAllAppointments().stream()
                .map(AppointmentMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(AppointmentMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequestDTO requestDTO) {
        Appointment appointment = AppointmentMapper.toEntity(requestDTO);
        Appointment updated = appointmentService.updateAppointment(id, appointment);
        return ResponseEntity.ok(AppointmentMapper.toResponseDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}