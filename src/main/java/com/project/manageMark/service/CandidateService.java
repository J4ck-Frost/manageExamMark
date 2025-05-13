package com.project.manageMark.service;

import com.project.manageMark.dto.Request.CandidateRequest;
import com.project.manageMark.dto.Response.CandidateResponse;

import java.util.List;

public interface CandidateService {
    CandidateResponse createCandidate(CandidateRequest request);
    List<CandidateResponse> getAllCandidates();
    CandidateResponse getCandidateById(Long id);
    CandidateResponse updateCandidate(Long id, CandidateRequest request);
    void deleteCandidate(Long id);
}