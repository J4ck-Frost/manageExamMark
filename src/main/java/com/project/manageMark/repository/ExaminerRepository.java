package com.project.manageMark.repository;

import com.project.manageMark.entity.Examiner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminerRepository extends JpaRepository<Examiner, Long> {
    List<Examiner> findExaminerByName(String name);

    boolean existsByEmail(String email);
}
