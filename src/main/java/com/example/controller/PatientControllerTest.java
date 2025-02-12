package com.example.controller;

import com.example.entity.Patient;
import com.example.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;

    private Patient patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");
        patient.setAge(30);
        patient.setGender("Male");
    }

    @Test
    void addPatient() {
        when(patientService.addPatient(any(Patient.class))).thenReturn(patient);

        ResponseEntity<Patient> response = patientController.addPatient(patient);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).addPatient(any(Patient.class));
    }

    @Test
    void getPatientById() {
        when(patientService.getPatientById(1L)).thenReturn(patient);

        ResponseEntity<Patient> response = patientController.getPatientById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).getPatientById(1L);
    }

    @Test
    void getPatientById_NotFound() {
        when(patientService.getPatientById(2L)).thenThrow(new ResourceNotFoundException("Patient not found"));

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            patientController.getPatientById(2L);
        });

        assertEquals("Patient not found", exception.getMessage());
        verify(patientService, times(1)).getPatientById(2L);
    }
}