package com.example.service;

import com.example.controller.HeartRateController;
import com.example.entity.HeartRate;
import com.example.entity.Patient;
import com.example.repository.HeartRateRepository;
import com.example.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HeartRateController.class)
public class HeartRateServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeartRateService heartRateService;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private HeartRateRepository heartRateRepository;

    private HeartRate heartRate;
    private Patient patient;

    @BeforeEach
    public void setUp() {
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
    public void testRecordHeartRate() throws Exception {
        when(heartRateService.recordHeartRate(any(HeartRate.class))).thenReturn(heartRate);

        mockMvc.perform(post("/heartrate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"patient\":{\"id\":1},\"timestamp\":\"2025-02-12T00:00:00\",\"heartRateValue\":75}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetHeartRatesByPatientId() throws Exception {
        when(heartRateService.getHeartRatesByPatientId(1L)).thenReturn(Collections.singletonList(heartRate));

        mockMvc.perform(get("/heartrate/1"))
                .andExpect(status().isOk());
    }
}