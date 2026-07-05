package com.clinicerp.backend.controller;

import com.clinicerp.backend.dto.DoctorRequestDTO;
import com.clinicerp.backend.dto.DoctorResponseDTO;
import com.clinicerp.backend.entity.Doctor;
import com.clinicerp.backend.mapper.DoctorMapper;
import com.clinicerp.backend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> registerDoctor(@RequestBody DoctorRequestDTO requestDTO) {
        Doctor doctor = DoctorMapper.toEntity(requestDTO);
        Doctor saved = doctorService.registerDoctor(doctor);
        return ResponseEntity.status(201).body(DoctorMapper.toResponseDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        List<DoctorResponseDTO> result = doctorService.getAllDoctors().stream()
                .map(DoctorMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id)
                .map(DoctorMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@PathVariable Long id, @RequestBody DoctorRequestDTO requestDTO) {
        Doctor doctor = DoctorMapper.toEntity(requestDTO);
        Doctor updated = doctorService.updateDoctor(id, doctor);
        return ResponseEntity.ok(DoctorMapper.toResponseDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}