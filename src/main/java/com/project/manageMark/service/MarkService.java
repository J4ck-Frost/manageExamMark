package com.project.manageMark.service;

import com.project.manageMark.dto.MarkDTO;
import java.util.List;

public interface MarkService {
    MarkDTO createMark(MarkDTO dto);
    MarkDTO getMarkById(Long id);
    MarkDTO updateMark(Long id, MarkDTO dto);
    void deleteMark(Long id);
    List<MarkDTO> getMarksByCandidateId(Long studentId);
    List<MarkDTO> getMarksByExamId(Long subjectId);
    Double getAverageScoreByCandidateId(Long studentId);
    Double getAverageScoreByExamId(Long subjectId);
}