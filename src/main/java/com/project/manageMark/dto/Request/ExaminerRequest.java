package com.project.manageMark.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ExaminerRequest {
    @NotBlank (message = "name must not empty")
    private String name;
    @NotBlank (message = "name must not empty")
    private String email;
}
