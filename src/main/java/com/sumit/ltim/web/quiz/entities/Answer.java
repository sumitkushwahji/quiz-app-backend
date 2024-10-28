package com.sumit.ltim.web.quiz.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userAnswer; // The answer chosen by the user

    @ManyToOne
    @JoinColumn(name = "attempt_id", nullable = false)
    private Attempt attempt; // Reference to the attempt

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question; // Reference to the question
}
