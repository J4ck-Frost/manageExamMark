package com.project.manageMark.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExaminerResponse {
    private Long id;
    private String name;
    private String email;
}
