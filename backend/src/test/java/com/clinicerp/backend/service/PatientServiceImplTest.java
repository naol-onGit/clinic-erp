package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.Patient;
import com.clinicerp.backend.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    void registerPatient_shouldSaveAndReturnPatient() {
        // arrange
        Patient patient = new Patient();
        patient.setFullName("Test Patient");

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        // act
        Patient result = patientService.registerPatient(patient);

        // assert
        assertEquals("Test Patient", result.getFullName());
        verify(patientRepository, times(1)).save(patient);
    }
    @Test
    void getPatientById_shouldReturnPatient_whenExists() {
        // arrange
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFullName("Test Patient");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        // act
        Optional<Patient> result = patientService.getPatientById(1L);

        // assert
        assertTrue(result.isPresent());
        assertEquals("Test Patient", result.get().getFullName());
        verify(patientRepository, times(1)).findById(1L);
    }

    @Test
    void getPatientById_shouldReturnEmpty_whenNotFound() {
        // arrange
        when(patientRepository.findById(999L)).thenReturn(Optional.empty());

        // act
        Optional<Patient> result = patientService.getPatientById(999L);

        // assert
        assertTrue(result.isEmpty());
        verify(patientRepository, times(1)).findById(999L);
    }

    @Test
    void getAllPatients_shouldReturnListOfPatients() {
        // arrange
        Patient patient1 = new Patient();
        patient1.setFullName("Patient One");
        Patient patient2 = new Patient();
        patient2.setFullName("Patient Two");

        when(patientRepository.findAll()).thenReturn(List.of(patient1, patient2));

        // act
        List<Patient> result = patientService.getAllPatients();

        // assert
        assertEquals(2, result.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void deletePatient_shouldCallRepositoryDelete() {
        // act
        patientService.deletePatient(1L);

        // assert
        verify(patientRepository, times(1)).deleteById(1L);
    }
}