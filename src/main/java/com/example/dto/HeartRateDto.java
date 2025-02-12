package com.example.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HeartRateDto {
    private Long id;
    private LocalDateTime timestamp;
    private int heartRateValue;
    private Long patientId;
}