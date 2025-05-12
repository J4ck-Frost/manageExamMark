package com.project.manageMark.service;

import com.project.manageMark.dto.CandidateDTO;
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
    public CandidateDTO createCandidate(CandidateDTO dto) {
        if (candidateRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Candidate candidate = new Candidate();
        candidate.setName(dto.getName());
        candidate.setEmail(dto.getEmail());

        Candidate savedCandidate = candidateRepository.save(candidate);
        return toDTO(savedCandidate);
    }

    @Override
    public List<CandidateDTO> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateDTO getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
        return toDTO(candidate);
    }

    @Override
    public CandidateDTO updateCandidate(Long id, CandidateDTO dto) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        if (!candidate.getEmail().equals(dto.getEmail())) {
            if (candidateRepository.existsByEmail(dto.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
        }

        candidate.setName(dto.getName());
        candidate.setEmail(dto.getEmail());

        Candidate updatedCandidate = candidateRepository.save(candidate);
        return toDTO(updatedCandidate);
    }

    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
        candidateRepository.delete(candidate);
    }

    private CandidateDTO toDTO(Candidate candidate) {
        CandidateDTO dto = new CandidateDTO();
        dto.setName(candidate.getName());
        dto.setEmail(candidate.getEmail());
        return dto;
    }
}