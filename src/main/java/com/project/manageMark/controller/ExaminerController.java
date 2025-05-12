package com.project.manageMark.controller;

import com.project.manageMark.dto.ExaminerDTO;
import com.project.manageMark.service.ExaminerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examiners")
public class ExaminerController {

    private final ExaminerService examinerService;

    public ExaminerController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @PostMapping
    public ResponseEntity<ExaminerDTO> createExaminer(@RequestBody @Valid ExaminerDTO dto) {
        ExaminerDTO created = examinerService.createExaminer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ExaminerDTO>> getAllExaminers() {
        return ResponseEntity.ok(examinerService.getAllExaminers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExaminerDTO> getExaminerById(@PathVariable Long id) {
        return ResponseEntity.ok(examinerService.getExaminerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExaminerDTO> updateExaminer(
            @PathVariable Long id,
            @RequestBody @Valid ExaminerDTO dto) {
        return ResponseEntity.ok(examinerService.updateExaminer(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExaminer(@PathVariable Long id) {
        examinerService.deleteExaminer(id);
        return ResponseEntity.noContent().build();
    }
}