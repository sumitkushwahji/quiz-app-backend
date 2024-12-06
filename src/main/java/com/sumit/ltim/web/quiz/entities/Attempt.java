package com.sumit.ltim.web.quiz.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attempt_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int score;

    private Boolean isCompleted = false;

    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Answer> answers;


    @Override
    public String toString() {
        return "Attempt{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", score=" + score +
                ", isCompleted=" + isCompleted +
                '}';
    }

}
