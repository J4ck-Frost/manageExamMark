package com.project.manageMark.service;

import com.project.manageMark.dto.Request.ExaminerRequest;
import com.project.manageMark.dto.Response.ExaminerResponse;
import com.project.manageMark.entity.Examiner;
import com.project.manageMark.exception.ResourceNotFoundException;
import com.project.manageMark.repository.ExaminerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ExaminerServiceImpl implements ExaminerService {

    private final ExaminerRepository examinerRepository;

    @Override
    public ExaminerResponse createExaminer(ExaminerRequest request) {
        if (examinerRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Examiner examiner = new Examiner();
        examiner.setName(request.getName());
        examiner.setEmail(request.getEmail());

        Examiner savedExaminer = examinerRepository.save(examiner);
        return toDTO(savedExaminer);
    }

    @Override
    public List<ExaminerResponse> getAllExaminers() {
        return examinerRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExaminerResponse getExaminerById(Long id) {
        Examiner examiner = examinerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        return toDTO(examiner);
    }

    @Override
    public ExaminerResponse updateExaminer(Long id, ExaminerRequest request) {
        Examiner examiner = examinerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        if (!examiner.getEmail().equals(request.getEmail())) {
            if (examinerRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
        }

        examiner.setName(request.getName());
        examiner.setEmail(request.getEmail());

        Examiner updatedExaminer = examinerRepository.save(examiner);
        return toDTO(updatedExaminer);
    }

    @Override
    public void deleteExaminer(Long id) {
        Examiner examiner = examinerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        examinerRepository.delete(examiner);
    }

    private ExaminerResponse toDTO(Examiner examiner) {
        ExaminerResponse response = new ExaminerResponse();
        response.setId(examiner.getId());
        response.setName(examiner.getName());
        response.setEmail(examiner.getEmail());
        return response;
    }
}