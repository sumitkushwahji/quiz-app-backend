package com.sumit.ltim.web.quiz.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int rank;

    private int totalScore;
}
