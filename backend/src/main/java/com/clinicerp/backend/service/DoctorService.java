package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Doctor registerDoctor(Doctor doctor);
    Optional<Doctor> getDoctorById(Long id);
    List<Doctor> getAllDoctors();
    Doctor updateDoctor(Long id, Doctor doctor);
    void deleteDoctor(Long id);
}