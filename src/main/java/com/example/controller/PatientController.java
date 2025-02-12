package com.example.controller;

import com.example.dto.PatientDto;
import com.example.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public PatientDto createPatient(@RequestBody PatientDto patientDto) {
        return patientService.createPatient(patientDto);
    }

    @GetMapping("/{id}")
    public PatientDto getPatient(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @GetMapping("/user/{userId}")
    public List<PatientDto> getPatientsByUser(@PathVariable Long userId) {
        return patientService.getPatientDtosByUserId(userId);
    }
}