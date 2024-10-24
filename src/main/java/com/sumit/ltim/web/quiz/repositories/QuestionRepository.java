package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
