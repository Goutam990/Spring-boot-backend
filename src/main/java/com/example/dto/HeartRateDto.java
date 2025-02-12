package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeartRateDto {
    private Long id;
    private Long patientId;
    private LocalDateTime timestamp;
    private Integer heartRateValue;
}