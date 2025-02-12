package com.example.dto;

import lombok.Data;

@Data
public class PatientDto {
    private String name;
    private Integer age;
    private String gender;
    private Long userId;
}