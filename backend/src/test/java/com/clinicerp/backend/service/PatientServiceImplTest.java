package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.Patient;
import com.clinicerp.backend.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}