package com.project.manageMark.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExamDTO {
    @NotBlank
    private String name;
    private Long teacherId;
    @NotBlank
    private String description;
    @NotBlank
    private LocalDate startDate;
    @NotBlank
    private LocalDate endDate;
}