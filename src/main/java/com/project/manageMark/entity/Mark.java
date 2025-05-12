package com.project.manageMark.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mark {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Double score;

        @ManyToOne
        @JoinColumn(name = "exam_id")
        private Exam exam;

        @ManyToOne
        @JoinColumn(name = "candidate_id")
        private Candidate candidate;
}
