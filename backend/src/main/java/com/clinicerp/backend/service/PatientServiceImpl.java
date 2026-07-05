package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.Patient;
import com.clinicerp.backend.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    // implement all 5 methods here
    @Override
    public Patient registerPatient(Patient patient) {
        patient.setCreatedAt(LocalDateTime.now());
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        // this one's trickier - think about it below
        patient.setId(id);
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}