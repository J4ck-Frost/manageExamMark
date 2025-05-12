package com.project.manageMark.service;

import com.project.manageMark.dto.ExaminerDTO;

import java.util.List;

public interface ExaminerService {
    ExaminerDTO createExaminer(ExaminerDTO dto);
    List<ExaminerDTO> getAllExaminers();
    ExaminerDTO getExaminerById(Long id);
    ExaminerDTO updateExaminer(Long id, ExaminerDTO dto);
    void deleteExaminer(Long id);
}