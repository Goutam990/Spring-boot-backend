package com.example.service;

import com.example.entity.HeartRate;
import com.example.entity.Patient;
import com.example.repository.HeartRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class HeartRateServiceTest {

    @Mock
    private HeartRateRepository heartRateRepository;

    @InjectMocks
    private HeartRateService heartRateService;

    private HeartRate heartRate;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        patient = new Patient();
        patient.setName("John Doe");
        patient.setAge(30);
        patient.setGender("Male");

        heartRate = new HeartRate();
        heartRate.setPatient(patient);
        heartRate.setTimestamp(LocalDateTime.now());
        heartRate.setHeartRateValue(75);
    }

    @Test
    public void testRecordHeartRate() {
        when(heartRateRepository.save(any(HeartRate.class))).thenReturn(heartRate);

        HeartRate savedHeartRate = heartRateService.recordHeartRate(heartRate);

        assertEquals(heartRate.getHeartRateValue(), savedHeartRate.getHeartRateValue());
    }

    @Test
    public void testGetHeartRatesByPatientId() {
        when(heartRateRepository.findByPatientId(1L)).thenReturn(Collections.singletonList(heartRate));

        List<HeartRate> heartRates = heartRateService.getHeartRatesByPatientId(1L);

        assertEquals(1, heartRates.size());
        assertEquals(heartRate.getHeartRateValue(), heartRates.get(0).getHeartRateValue());
    }
}