package com.project.manageMark.service;

import com.project.manageMark.dto.MarkDTO;
import com.project.manageMark.entity.Candidate;
import com.project.manageMark.entity.Mark;
import com.project.manageMark.entity.Exam;
import com.project.manageMark.repository.MarkRepository;
import com.project.manageMark.repository.CandidateRepository;
import com.project.manageMark.repository.ExamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;
    private final CandidateRepository candidateRepository;
    private final ExamRepository examRepository;

    public MarkServiceImpl(MarkRepository markRepository,
                           CandidateRepository candidateRepository,
                           ExamRepository examRepository) {
        this.markRepository = markRepository;
        this.candidateRepository = candidateRepository;
        this.examRepository = examRepository;
    }

    @Override
    public MarkDTO createMark(MarkDTO dto) {
        Candidate candidate = candidateRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Exam exam = examRepository.findById(dto.getExamId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Mark mark = new Mark();
        mark.setScore(dto.getScore());
        mark.setCandidate(candidate);
        mark.setExam(exam);

        Mark savedMark = markRepository.save(mark);
        return toDTO(savedMark);
    }


    @Override
    public MarkDTO getMarkById(Long id) {
        Mark mark = markRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mark not found"));
        return toDTO(mark);
    }

    @Override
    public MarkDTO updateMark(Long id, MarkDTO dto) {
        Mark mark = markRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mark not found"));

        mark.setScore(dto.getScore());
        Mark updatedMark = markRepository.save(mark);
        return toDTO(updatedMark);
    }

    @Override
    public void deleteMark(Long id) {
        Mark mark = markRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mark not found"));
        markRepository.delete(mark);
    }

    @Override
    public List<MarkDTO> getMarksByCandidateId(Long candidateId) {
        return markRepository.findByCandidateId(candidateId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MarkDTO> getMarksByExamId(Long examId) {
        return markRepository.findByExamId(examId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Double getAverageScoreByCandidateId(Long candidateId) {
        return markRepository.findAverageScoreByCandidateId(candidateId);
    }

    @Override
    public Double getAverageScoreByExamId(Long examId) {
        return markRepository.findAverageScoreByCandidateId(examId);
    }

    private MarkDTO toDTO(Mark mark) {
        MarkDTO dto = new MarkDTO();
        dto.setScore(mark.getScore());
        dto.setStudentId(mark.getCandidate().getId());
        dto.setExamId(mark.getExam().getId());
        return dto;
    }
}