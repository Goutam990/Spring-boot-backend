package com.example.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class HeartRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private int heartRateValue;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}