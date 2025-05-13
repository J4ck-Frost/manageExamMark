package com.project.manageMark.service;

import com.project.manageMark.dto.Request.CandidateRequest;
import com.project.manageMark.dto.Response.CandidateResponse;
import com.project.manageMark.entity.Candidate;
import com.project.manageMark.exception.ResourceNotFoundException;
import com.project.manageMark.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public CandidateResponse createCandidate(CandidateRequest request) {
        if (candidateRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Candidate candidate = new Candidate();
        candidate.setName(request.getName());
        candidate.setEmail(request.getEmail());

        Candidate savedCandidate = candidateRepository.save(candidate);
        return toDTO(savedCandidate);
    }

    @Override
    public List<CandidateResponse> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateResponse getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
        return toDTO(candidate);
    }

    @Override
    public CandidateResponse updateCandidate(Long id, CandidateRequest request) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        if (!candidate.getEmail().equals(request.getEmail())) {
            if (candidateRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
        }

        candidate.setName(request.getName());
        candidate.setEmail(request.getEmail());

        Candidate updatedCandidate = candidateRepository.save(candidate);
        return toDTO(updatedCandidate);
    }

    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
        candidateRepository.delete(candidate);
    }

    private CandidateResponse toDTO(Candidate candidate) {
        CandidateResponse response = new CandidateResponse();
        response.setId(candidate.getId());
        response.setName(candidate.getName());
        response.setEmail(candidate.getEmail());
        return response;
    }
}