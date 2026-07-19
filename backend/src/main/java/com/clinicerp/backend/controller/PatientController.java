package com.clinicerp.backend.controller;

import com.clinicerp.backend.dto.PatientRequestDTO;
import com.clinicerp.backend.dto.PatientResponseDTO;
import com.clinicerp.backend.entity.Patient;
import com.clinicerp.backend.mapper.PatientMapper;
import com.clinicerp.backend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> registerPatient(@RequestBody PatientRequestDTO requestDTO) {
        Patient patient = PatientMapper.toEntity(requestDTO);
        Patient savedPatient = patientService.registerPatient(patient);
        PatientResponseDTO responseDTO = PatientMapper.toResponseDTO(savedPatient);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        List<PatientResponseDTO> responseDTOs = patients.stream()
                .map(PatientMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(PatientMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id, @RequestBody PatientRequestDTO requestDTO) {
        Patient patient = PatientMapper.toEntity(requestDTO);
        Patient updatedPatient = patientService.updatePatient(id, patient);
        PatientResponseDTO responseDTO = PatientMapper.toResponseDTO(updatedPatient);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}