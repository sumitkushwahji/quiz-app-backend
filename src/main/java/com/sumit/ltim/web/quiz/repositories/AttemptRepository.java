package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.entities.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    Optional<Attempt> findByUserIdAndTestId(Long userId, Long testId);
    List<Attempt> findByUserId(Long userId);
    List<Attempt> findAllByUserId(Long userId);


    long countByIsCompleted(boolean isCompleted);

    @Query("SELECT AVG(a.score) FROM Attempt a WHERE a.isCompleted = true")
    Double calculateAverageScore();
}
