package com.project.manageMark.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExamRequest {
    @NotBlank(message = "name must not be null")
    private String name;
    private Long teacherId;
    @NotBlank (message = "description must not be null")
    private String description;
    @NotBlank
    private LocalDate startDate;
    @NotBlank
    private LocalDate endDate;
}
