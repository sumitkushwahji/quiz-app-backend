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

    private String explanation;

    private String subject;

    private String topic;

    private String exam;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;  // Reference to the Test entity

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;
}
