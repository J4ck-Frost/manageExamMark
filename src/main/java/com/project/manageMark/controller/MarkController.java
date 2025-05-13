package com.project.manageMark.controller;

import com.project.manageMark.dto.Request.MarkRequest;
import com.project.manageMark.dto.Request.UpdateMarkRequest;
import com.project.manageMark.dto.Response.MarkResponse;
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
        public ResponseEntity<MarkResponse> createMark(@RequestBody @Valid MarkRequest request) {
                MarkResponse created = markService.createMark(request);
                return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }


        @GetMapping("/{id}")
        public ResponseEntity<MarkResponse> getMarkById(@PathVariable Long id) {
                MarkResponse mark = markService.getMarkById(id);
                return ResponseEntity.ok(mark);
        }

        @PutMapping("/{id}")
        public ResponseEntity<MarkResponse> updateMark(
                @PathVariable Long id,
                @RequestBody @Valid UpdateMarkRequest request) {
                MarkResponse updatedMark = markService.updateMark(id, request);
                return ResponseEntity.ok(updatedMark);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteMark(@PathVariable Long id) {
                markService.deleteMark(id);
                return ResponseEntity.noContent().build();
        }

        @GetMapping("/candidate/{candidateId}")
        public ResponseEntity<List<MarkResponse>> getMarksByCandidateId(@PathVariable Long candidateId) {
                List<MarkResponse> marks = markService.getMarksByCandidateId(candidateId);
                return ResponseEntity.ok(marks);
        }

        @GetMapping("/exam/{examId}")
        public ResponseEntity<List<MarkResponse>> getMarksByExamId(@PathVariable Long subjectId) {
                List<MarkResponse> marks = markService.getMarksByExamId(subjectId);
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