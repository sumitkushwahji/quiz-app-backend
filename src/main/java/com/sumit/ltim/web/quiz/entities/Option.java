package com.sumit.ltim.web.quiz.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name= "question_id")
    @JsonBackReference
    private Question question;

}
