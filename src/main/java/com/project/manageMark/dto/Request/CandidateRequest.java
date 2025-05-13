package com.project.manageMark.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CandidateRequest {
    @NotBlank(message = "name must not be empty")
    private String name;
    @NotBlank(message = "email must not be empty")
    private String email;
}
