package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.entities.Attempt;
import com.sumit.ltim.web.quiz.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    List<Attempt> findByTest(Test test);
}