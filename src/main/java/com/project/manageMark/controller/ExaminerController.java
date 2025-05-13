package com.project.manageMark.controller;

import com.project.manageMark.dto.Request.ExaminerRequest;
import com.project.manageMark.dto.Response.ExaminerResponse;
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
    public ResponseEntity<ExaminerResponse> createExaminer(@RequestBody @Valid ExaminerRequest request) {
        ExaminerResponse created = examinerService.createExaminer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ExaminerResponse>> getAllExaminers() {
        return ResponseEntity.ok(examinerService.getAllExaminers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExaminerResponse> getExaminerById(@PathVariable Long id) {
        return ResponseEntity.ok(examinerService.getExaminerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExaminerResponse> updateExaminer(
            @PathVariable Long id,
            @RequestBody @Valid ExaminerRequest request) {
        return ResponseEntity.ok(examinerService.updateExaminer(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExaminer(@PathVariable Long id) {
        examinerService.deleteExaminer(id);
        return ResponseEntity.noContent().build();
    }
}