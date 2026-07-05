package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.Doctor;
import com.clinicerp.backend.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public Doctor registerDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        doctor.setId(id);
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}