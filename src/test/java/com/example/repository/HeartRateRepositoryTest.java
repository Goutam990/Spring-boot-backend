package com.example.repository;

import com.example.entity.HeartRate;
import com.example.entity.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class HeartRateRepositoryTest {

    @Autowired
    private HeartRateRepository heartRateRepository;

    @Autowired
    private PatientRepository patientRepository;

    private HeartRate heartRate;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
        patient.setName("John Doe");
        patient.setAge(30);
        patient.setGender("Male");
        patient = patientRepository.save(patient);

        heartRate = new HeartRate();
        heartRate.setPatient(patient);
        heartRate.setTimestamp(LocalDateTime.now());
        heartRate.setHeartRateValue(75);
    }

    @Test
    @Rollback(false)
    public void testSaveHeartRate() {
        HeartRate savedHeartRate = heartRateRepository.save(heartRate);
        assertThat(savedHeartRate).isNotNull();
        assertThat(savedHeartRate.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindHeartRateById() {
        HeartRate savedHeartRate = heartRateRepository.save(heartRate);
        Optional<HeartRate> foundHeartRate = heartRateRepository.findById(savedHeartRate.getId());
        assertThat(foundHeartRate).isPresent();
        assertThat(foundHeartRate.get().getHeartRateValue()).isEqualTo(heartRate.getHeartRateValue());
    }

    @Test
    public void testFindHeartRatesByPatientId() {
        heartRateRepository.save(heartRate);
        List<HeartRate> heartRates = heartRateRepository.findByPatientId(patient.getId());
        assertThat(heartRates).isNotEmpty();
    }
}