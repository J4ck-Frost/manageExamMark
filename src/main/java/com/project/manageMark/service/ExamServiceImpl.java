package com.project.manageMark.service;

import com.project.manageMark.dto.ExamDTO;
import com.project.manageMark.entity.Exam;
import com.project.manageMark.entity.Examiner;
import com.project.manageMark.exception.ResourceNotFoundException;
import com.project.manageMark.repository.ExamRepository;
import com.project.manageMark.repository.ExaminerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ExaminerRepository examinerRepository;

    @Override
    public ExamDTO createExam(ExamDTO dto) {
        Examiner examiner = examinerRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        Exam exam = new Exam();
        exam.setName(dto.getName());
        exam.setExaminer(examiner);
        exam.setDescription(dto.getDescription());
        exam.setStartDate(dto.getStartDate());
        exam.setEndDate(dto.getEndDate());

        Exam savedExam = examRepository.save(exam);
        return toDTO(savedExam);
    }

    @Override
    public List<ExamDTO> getAllExams() {
        return examRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExamDTO getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
        return toDTO(exam);
    }

    @Override
    public ExamDTO updateExam(Long id, ExamDTO dto) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        Examiner examiner = examinerRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        exam.setName(dto.getName());
        exam.setExaminer(examiner);
        exam.setDescription(dto.getDescription());
        exam.setStartDate(dto.getStartDate());
        exam.setEndDate(dto.getEndDate());

        Exam updatedExam = examRepository.save(exam);
        return toDTO(updatedExam);
    }

    @Override
    public void deleteExam(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
        examRepository.delete(exam);
    }

    @Override
        public List<ExamDTO> getExamsByExaminerId(Long teacherId) {
        return examRepository.findByExaminerId(teacherId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ExamDTO toDTO(Exam exam) {
        ExamDTO dto = new ExamDTO();
        dto.setName(exam.getName());
        dto.setTeacherId(exam.getExaminer().getId());
        dto.setEndDate(exam.getEndDate());
        dto.setDescription(exam.getDescription());
        dto.setStartDate(exam.getStartDate());
        return dto;
    }
}