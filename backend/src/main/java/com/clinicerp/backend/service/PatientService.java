package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    Patient registerPatient(Patient patient);
    Optional<Patient> getPatientById(Long id);
    List<Patient> getAllPatients();
    Patient updatePatient(Long id, Patient patient);
    void deletePatient(Long id);
}