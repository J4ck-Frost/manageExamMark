package com.project.manageMark.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"candidate_id", "exam_id"})
)
public class Mark {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Double score;

        @ManyToOne
        @JoinColumn(name = "exam_id",nullable = false)
        private Exam exam;

        @ManyToOne
        @JoinColumn(name = "candidate_id", nullable = false)
        private Candidate candidate;
}
