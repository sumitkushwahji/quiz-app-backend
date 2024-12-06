package com.sumit.ltim.web.quiz.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    private String text;
    private String explanation; // Optional for questions
    private String subject;
    private String topic;
    private String exam;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType; // Enum for question type

    private String difficulty; // For easy, medium, hard categorization

    @ManyToMany(mappedBy = "questions")
    @JsonBackReference // Prevent cyclic serialization
    private List<Test> tests; // Link to multiple tests

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Option> options;
}


