package com.project.manageMark.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MarkDTO {

        @Max(value = 10, message = "Score must be <=10")
        @Min(value = 0, message = "Score must be >=0")
        private Double score;
        @NotNull
        private Long examId;
        @NotNull
        private Long studentId;
}
