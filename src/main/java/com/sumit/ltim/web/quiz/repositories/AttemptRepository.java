package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.entities.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    Optional<Attempt> findByUserIdAndTestId(Long userId, Long testId);
    List<Attempt> findByUserId(Long userId);
    List<Attempt> findAllByUserId(Long userId);

}
