package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.Doctor;
import com.clinicerp.backend.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorServiceImplTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @Test
    void registerDoctor_shouldSaveAndReturnDoctor() {
        Doctor doctor = new Doctor();
        doctor.setFullName("Dr. Test");
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        Doctor result = doctorService.registerDoctor(doctor);

        assertEquals("Dr. Test", result.getFullName());
        verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    void getDoctorById_shouldReturnDoctor_whenExists() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        Optional<Doctor> result = doctorService.getDoctorById(1L);

        assertTrue(result.isPresent());
        verify(doctorRepository, times(1)).findById(1L);
    }

    @Test
    void getDoctorById_shouldReturnEmpty_whenNotFound() {
        when(doctorRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Doctor> result = doctorService.getDoctorById(999L);

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllDoctors_shouldReturnList() {
        when(doctorRepository.findAll()).thenReturn(List.of(new Doctor(), new Doctor()));

        List<Doctor> result = doctorService.getAllDoctors();

        assertEquals(2, result.size());
    }

    @Test
    void deleteDoctor_shouldCallRepositoryDelete() {
        doctorService.deleteDoctor(1L);
        verify(doctorRepository, times(1)).deleteById(1L);
    }
}