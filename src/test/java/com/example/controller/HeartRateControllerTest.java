package com.example.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.entity.HeartRate;
import com.example.service.HeartRateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;

public class HeartRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private HeartRateService heartRateService;

    @InjectMocks
    private HeartRateController heartRateController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(heartRateController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testRecordHeartRate() throws Exception {
        HeartRate heartRate = new HeartRate();
        heartRate.setId(1L);
        heartRate.setTimestamp(LocalDateTime.now());
        heartRate.setHeartRateValue(75);

        when(heartRateService.recordHeartRate(any(HeartRate.class))).thenReturn(heartRate);

        mockMvc.perform(post("/heartrate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(heartRate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.heartRateValue").value(75));
    }

    @Test
    public void testGetHeartRateByPatientId() throws Exception {
        HeartRate heartRate = new HeartRate();
        heartRate.setId(1L);
        heartRate.setTimestamp(LocalDateTime.now());
        heartRate.setHeartRateValue(75);

        when(heartRateService.getHeartRatesByPatientId(1L)).thenReturn(Collections.singletonList(heartRate));

        mockMvc.perform(get("/heartrate/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].heartRateValue").value(75));
    }
}