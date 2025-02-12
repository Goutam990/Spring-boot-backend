package com.example.repository;

import com.example.entity.HeartRate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HeartRateRepository extends JpaRepository<HeartRate, Long> {
    List<HeartRate> findByPatientId(Long patientId);
}