package com.project.manageMark.repository;

import com.project.manageMark.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    boolean existsByName(String name);

    List<Exam> findByExaminerId(Long examinerId);
}
