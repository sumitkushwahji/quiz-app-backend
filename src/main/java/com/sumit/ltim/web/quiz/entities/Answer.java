package com.sumit.ltim.web.quiz.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    private String userAnswer; // The ID of the selected option


    @ToString.Exclude
    @JoinColumn(name = "attempt_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Attempt attempt;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", userAnswer='" + userAnswer + '\'' +
                ", questionId=" + (question != null ? question.getId() : null) +
                '}';
    }
}
