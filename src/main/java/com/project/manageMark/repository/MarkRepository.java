package com.project.manageMark.repository;

import com.project.manageMark.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
    List<Mark> findByCandidateId(Long candidateId);
    List<Mark> findByExamId(Long examId);

    @Query("SELECT AVG(m.score) FROM Mark m WHERE m.candidate.id = :candidateId")
    Double findAverageScoreByCandidateId(Long candidateId);

    @Query("SELECT AVG(m.score) FROM Mark m WHERE m.exam.id = :examId")
    Double findAverageScoreByExamId(Long examId);
}