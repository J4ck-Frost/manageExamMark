package com.project.manageMark.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkResponse {
    private Double score;
    private Long examId;
    private Long studentId;
}
