package com.project.manageMark.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CandidateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
}
