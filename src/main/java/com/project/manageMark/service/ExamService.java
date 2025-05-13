package com.project.manageMark.service;

import com.project.manageMark.dto.Request.ExamRequest;
import com.project.manageMark.dto.Response.ExamResponse;

import java.util.List;

public interface ExamService {
    ExamResponse createExam(ExamRequest request);
    List<ExamResponse> getAllExams();
    ExamResponse getExamById(Long id);
    ExamResponse updateExam(Long id, ExamRequest request);
    void deleteExam(Long id);
    List<ExamResponse> getExamsByExaminerId(Long teacherId);
}