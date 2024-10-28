package com.sumit.ltim.web.quiz.repositories;


import com.sumit.ltim.web.quiz.entities.Answer;
import com.sumit.ltim.web.quiz.entities.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByAttempt(Attempt attempt);
}