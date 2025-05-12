package com.project.manageMark.service;

import com.project.manageMark.dto.CandidateDTO;
import java.util.List;

public interface CandidateService {
    CandidateDTO createCandidate(CandidateDTO dto);
    List<CandidateDTO> getAllCandidates();
    CandidateDTO getCandidateById(Long id);
    CandidateDTO updateCandidate(Long id, CandidateDTO dto);
    void deleteCandidate(Long id);
}