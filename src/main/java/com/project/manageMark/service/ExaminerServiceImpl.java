package com.project.manageMark.service;

import com.project.manageMark.dto.ExaminerDTO;
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
    public ExaminerDTO createExaminer(ExaminerDTO dto) {
        if (examinerRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Examiner examiner = new Examiner();
        examiner.setName(dto.getName());
        examiner.setEmail(dto.getEmail());

        Examiner savedExaminer = examinerRepository.save(examiner);
        return toDTO(savedExaminer);
    }

    @Override
    public List<ExaminerDTO> getAllExaminers() {
        return examinerRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExaminerDTO getExaminerById(Long id) {
        Examiner examiner = examinerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        return toDTO(examiner);
    }

    @Override
    public ExaminerDTO updateExaminer(Long id, ExaminerDTO dto) {
        Examiner examiner = examinerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        if (!examiner.getEmail().equals(dto.getEmail())) {
            if (examinerRepository.existsByEmail(dto.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
        }

        examiner.setName(dto.getName());
        examiner.setEmail(dto.getEmail());

        Examiner updatedExaminer = examinerRepository.save(examiner);
        return toDTO(updatedExaminer);
    }

    @Override
    public void deleteExaminer(Long id) {
        Examiner examiner = examinerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        examinerRepository.delete(examiner);
    }

    private ExaminerDTO toDTO(Examiner examiner) {
        ExaminerDTO dto = new ExaminerDTO();
        dto.setName(examiner.getName());
        dto.setEmail(examiner.getEmail());
        return dto;
    }
}