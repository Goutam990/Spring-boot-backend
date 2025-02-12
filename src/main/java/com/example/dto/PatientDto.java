package com.example.dto;

import lombok.Data;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@Data
public class PatientDto {
    private String name;
    private Integer age;
    private String gender;
    private Long userId; // Declare the userId field

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}