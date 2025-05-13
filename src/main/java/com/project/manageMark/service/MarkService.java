package com.project.manageMark.service;

import com.project.manageMark.dto.Request.MarkRequest;
import com.project.manageMark.dto.Request.UpdateMarkRequest;
import com.project.manageMark.dto.Response.MarkResponse;

import java.util.List;

public interface MarkService {
    MarkResponse createMark(MarkRequest request);
    MarkResponse getMarkById(Long id);
    MarkResponse updateMark(Long id, UpdateMarkRequest request);
    void deleteMark(Long id);
    List<MarkResponse> getMarksByCandidateId(Long candidateId);
    List<MarkResponse> getMarksByExamId(Long examId);
    Double getAverageScoreByCandidateId(Long candidateId);
    Double getAverageScoreByExamId(Long examId);
}