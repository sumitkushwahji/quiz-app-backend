package com.sumit.ltim.web.quiz.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String explanation; // Optional for questions
    private String subject;
    private String topic;
    private String exam;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType; // Enum for question type

    private String difficulty; // For easy, medium, hard categorization

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;
}
