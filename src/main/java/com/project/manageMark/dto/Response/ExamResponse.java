package com.project.manageMark.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponse {
    private Long id;
    private String name;
    private Long teacherId;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
