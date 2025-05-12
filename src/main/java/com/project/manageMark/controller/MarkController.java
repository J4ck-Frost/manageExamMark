package com.project.manageMark.controller;

import com.project.manageMark.dto.MarkDTO;
import com.project.manageMark.service.MarkService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
public class MarkController {

        private final MarkService markService;

        public MarkController(MarkService markService) {
                this.markService = markService;
        }

        @PostMapping
        public ResponseEntity<MarkDTO> createMark(@RequestBody @Valid MarkDTO dto) {
                MarkDTO created = markService.createMark(dto);
                return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }


        @GetMapping("/{id}")
        public ResponseEntity<MarkDTO> getMarkById(@PathVariable Long id) {
                MarkDTO mark = markService.getMarkById(id);
                return ResponseEntity.ok(mark);
        }

        @PutMapping("/{id}")
        public ResponseEntity<MarkDTO> updateMark(
                @PathVariable Long id,
                @RequestBody @Valid MarkDTO dto) {
                MarkDTO updatedMark = markService.updateMark(id, dto);
                return ResponseEntity.ok(updatedMark);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteMark(@PathVariable Long id) {
                markService.deleteMark(id);
                return ResponseEntity.noContent().build();
        }

        @GetMapping("/candidate/{candidateId}")
        public ResponseEntity<List<MarkDTO>> getMarksByCandidateId(@PathVariable Long candidateId) {
                List<MarkDTO> marks = markService.getMarksByCandidateId(candidateId);
                return ResponseEntity.ok(marks);
        }

        @GetMapping("/exam/{examId}")
        public ResponseEntity<List<MarkDTO>> getMarksByExamId(@PathVariable Long subjectId) {
                List<MarkDTO> marks = markService.getMarksByExamId(subjectId);
                return ResponseEntity.ok(marks);
        }

        @GetMapping("/exam/average")
                public ResponseEntity<Double> getAverageMarksByExamId(@PathVariable Long examId) {
                Double marks = markService.getAverageScoreByExamId(examId);
                return ResponseEntity.ok(marks);
        }

        @GetMapping("/candidate/average")
        public ResponseEntity<Double> getAverageMarksByCandidateId(@PathVariable Long candidateId) {
                Double marks = markService.getAverageScoreByCandidateId(candidateId);
                return ResponseEntity.ok(marks);
        }
}