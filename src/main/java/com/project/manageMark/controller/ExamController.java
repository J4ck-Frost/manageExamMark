package com.project.manageMark.controller;

import com.project.manageMark.dto.ExamDTO;
import com.project.manageMark.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ResponseEntity<ExamDTO> createExam(@RequestBody @Valid ExamDTO dto) {
        ExamDTO created = examService.createExam(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable Long id) {
        return ResponseEntity.ok(examService.getExamById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> updateExam(
            @PathVariable Long id,
            @RequestBody @Valid ExamDTO dto) {
        return ResponseEntity.ok(examService.updateExam(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/examiner/{examinerId}")
    public ResponseEntity<List<ExamDTO>> getExamsByExaminerId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(examService.getExamsByExaminerId(teacherId));
    }
}