package com.clinicerp.backend.repository;

import com.clinicerp.backend.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByFullNameContainingIgnoreCase(String fullName, Pageable pageable);
}