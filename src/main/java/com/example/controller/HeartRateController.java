package com.example.controller;

import com.example.dto.HeartRateDto;
import com.example.service.HeartRateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heart-rates")
public class HeartRateController {

    private final HeartRateService heartRateService;

    public HeartRateController(HeartRateService heartRateService) {
        this.heartRateService = heartRateService;
    }

    @PostMapping
    public HeartRateDto recordHeartRate(@RequestBody HeartRateDto heartRateDto) {
        return heartRateService.createHeartRate(heartRateDto);
    }

    @GetMapping("/patient/{patientId}")
    public List<HeartRateDto> getHeartRatesByPatient(@PathVariable Long patientId) {
        return heartRateService.getHeartRatesByPatientId(patientId);
    }
}