package com.project.manageMark.service;

import com.project.manageMark.dto.ExamDTO;
import java.util.List;

public interface ExamService {
    ExamDTO createExam(ExamDTO dto);
    List<ExamDTO> getAllExams();
    ExamDTO getExamById(Long id);
    ExamDTO updateExam(Long id, ExamDTO dto);
    void deleteExam(Long id);
    List<ExamDTO> getExamsByExaminerId(Long teacherId);
}