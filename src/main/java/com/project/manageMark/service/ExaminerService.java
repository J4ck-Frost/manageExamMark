package com.project.manageMark.service;

import com.project.manageMark.dto.Request.ExaminerRequest;
import com.project.manageMark.dto.Response.ExaminerResponse;

import java.util.List;

public interface ExaminerService {
    ExaminerResponse createExaminer(ExaminerRequest request);
    List<ExaminerResponse> getAllExaminers();
    ExaminerResponse getExaminerById(Long id);
    ExaminerResponse updateExaminer(Long id, ExaminerRequest request);
    void deleteExaminer(Long id);
}