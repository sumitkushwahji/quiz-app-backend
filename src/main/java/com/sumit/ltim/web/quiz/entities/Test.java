package com.sumit.ltim.web.quiz.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Test {
    @Id
    @Column(name = "test_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Name of the test (e.g., "Java Basics Test")

    private String description; // Description of the test

    private String subject; // Optional: Subject of the test (e.g., Java, Math)

    private String topic; // Optional: Topic of the test (e.g., "Spring Boot")

    private String exam; // Optional: Exam for the test (e.g., "Midterm", "Final")

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty; // Difficulty level (EASY, MEDIUM, HARD)

    private Integer duration; // Duration of the test in minutes

    private LocalDateTime startTime; // Start time for the test

    private LocalDateTime endTime; // End time for the test

    private LocalDateTime createdDate = LocalDateTime.now(); // When the test was created

    private Integer questionCount; // Number of questions in the test

    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "test_question",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions; // The list of questions included in the test
}
