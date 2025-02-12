package com.example.controller;

import com.example.entity.HeartRate;
import com.example.service.HeartRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heartrate")
public class HeartRateController {

    @Autowired
    private HeartRateService heartRateService;

    @PostMapping
    public ResponseEntity<HeartRate> recordHeartRate(@RequestBody HeartRate heartRate) {
        return ResponseEntity.ok(heartRateService.recordHeartRate(heartRate));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<HeartRate>> getHeartRatesByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(heartRateService.getHeartRatesByPatientId(patientId));
    }
}