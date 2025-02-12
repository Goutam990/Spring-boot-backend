package com.example.service;

import com.example.dto.HeartRateDto;
import com.example.entity.HeartRate;
import com.example.entity.Patient;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.HeartRateRepository;
import com.example.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeartRateService {

    @Autowired
    private HeartRateRepository heartRateRepository;

    @Autowired
    private PatientRepository patientRepository;

    public HeartRateDto createHeartRate(HeartRateDto heartRateDto) {
        HeartRate heartRate = new HeartRate();
        heartRate.setTimestamp(heartRateDto.getTimestamp());
        heartRate.setHeartRateValue(heartRateDto.getHeartRateValue());
        heartRate.setPatient(getPatientById(heartRateDto.getPatientId()));

        HeartRate savedHeartRate = heartRateRepository.save(heartRate);
        return convertToDto(savedHeartRate);
    }

    public List<HeartRateDto> getHeartRatesByPatientId(Long patientId) {
        List<HeartRate> heartRates = heartRateRepository.findByPatientId(patientId);
        return heartRates.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public HeartRateDto getHeartRateById(Long id) {
        return heartRateRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Heart rate not found with id: " + id));
    }

    public void deleteHeartRate(Long id) {
        heartRateRepository.deleteById(id);
    }

    private HeartRateDto convertToDto(HeartRate heartRate) {
        HeartRateDto heartRateDto = new HeartRateDto();
        heartRateDto.setId(heartRate.getId());
        heartRateDto.setTimestamp(heartRate.getTimestamp());
        heartRateDto.setHeartRateValue(heartRate.getHeartRateValue());
        heartRateDto.setPatientId(heartRate.getPatient().getId());
        return heartRateDto;
    }

    private Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + patientId));
    }
}