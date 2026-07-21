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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> registerPatient(@Valid @RequestBody PatientRequestDTO requestDTO) {
        Patient patient = PatientMapper.toEntity(requestDTO);
        Patient savedPatient = patientService.registerPatient(patient);
        PatientResponseDTO responseDTO = PatientMapper.toResponseDTO(savedPatient);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PatientResponseDTO>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search) {

        Pageable pageable = PageRequest.of(page, size);

        Page<PatientResponseDTO> result;
        if (search != null && !search.isBlank()) {
            result = patientService.searchPatients(search, pageable)
                    .map(PatientMapper::toResponseDTO);
        } else {
            result = patientService.getAllPatients(pageable)
                    .map(PatientMapper::toResponseDTO);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(PatientMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientRequestDTO requestDTO) {
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