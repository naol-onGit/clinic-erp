package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    Patient registerPatient(Patient patient);
    Optional<Patient> getPatientById(Long id);
    List<Patient> getAllPatients();
    Patient updatePatient(Long id, Patient patient);
    void deletePatient(Long id);
    Page<Patient> getAllPatients(Pageable pageable);
    Page<Patient> searchPatients(String name, Pageable pageable);
}