package com.example.service;

import com.example.dto.PatientDto;
import com.example.entity.Patient;
import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.PatientRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientDto createPatient(PatientDto patientDto) {
        User user = userRepository.findById(patientDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        patient.setAge(patientDto.getAge());
        patient.setGender(patientDto.getGender());
        patient.setUser(user);

        Patient savedPatient = patientRepository.save(patient);
        return convertToDto(savedPatient);
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        return convertToDto(patient);
    }

    public List<Patient> getPatientsByUserId(Long userId) {
        return patientRepository.findByUserId(userId);
    }

    public List<PatientDto> getPatientDtosByUserId(Long userId) {
        return patientRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PatientDto convertToDto(Patient patient) {
        PatientDto dto = new PatientDto();
        dto.setUserId(patient.getId());
        dto.setName(patient.getName());
        dto.setAge(patient.getAge());
        dto.setGender(patient.getGender());
        dto.setUserId(patient.getUser().getId());
        return dto;
    }
}