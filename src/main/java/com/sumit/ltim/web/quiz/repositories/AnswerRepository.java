package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.entities.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByAttemptId(Long attemptId);

    @Query("SELECT a FROM Answer a WHERE a.attempt.id = :attemptId AND a.question.id = :questionId")
    Answer findByAttemptIdAndQuestionId(@Param("attemptId") Long attemptId, @Param("questionId") Long questionId);

    // Optional flush method for immediate persistence
    @Modifying
    @Transactional
    void flush();

}
