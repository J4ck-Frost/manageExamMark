package com.project.manageMark.repository;

import com.project.manageMark.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findCandidateByName(String studentName);
    boolean existsByEmail(String email);
}
