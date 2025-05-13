package com.project.manageMark.service;

import com.project.manageMark.dto.Request.ExamRequest;
import com.project.manageMark.dto.Response.ExamResponse;
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
    public ExamResponse createExam(ExamRequest request) {
        Examiner examiner = examinerRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        Exam exam = new Exam();
        exam.setName(request.getName());
        exam.setExaminer(examiner);
        exam.setDescription(request.getDescription());
        exam.setStartDate(request.getStartDate());
        exam.setEndDate(request.getEndDate());

        Exam savedExam = examRepository.save(exam);
        return toDTO(savedExam);
    }

    @Override
    public List<ExamResponse> getAllExams() {
        return examRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExamResponse getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
        return toDTO(exam);
    }

    @Override
    public ExamResponse updateExam(Long id, ExamRequest request) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        Examiner examiner = examinerRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        exam.setName(request.getName());
        exam.setExaminer(examiner);
        exam.setDescription(request.getDescription());
        exam.setStartDate(request.getStartDate());
        exam.setEndDate(request.getEndDate());

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
        public List<ExamResponse> getExamsByExaminerId(Long teacherId) {
        return examRepository.findByExaminerId(teacherId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ExamResponse toDTO(Exam exam) {
        ExamResponse response = new ExamResponse();
        response.setId(exam.getId());
        response.setName(exam.getName());
        response.setTeacherId(exam.getExaminer().getId());
        response.setEndDate(exam.getEndDate());
        response.setDescription(exam.getDescription());
        response.setStartDate(exam.getStartDate());
        return response;
    }
}