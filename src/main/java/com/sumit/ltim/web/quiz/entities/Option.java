package com.sumit.ltim.web.quiz.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;
    @Column(name = "is_correct")
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name= "question_id") // Ensure this is set to not null
    @JsonBackReference
    private Question question;
}
