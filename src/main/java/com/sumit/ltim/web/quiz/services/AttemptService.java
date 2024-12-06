package com.sumit.ltim.web.quiz.services;

import com.sumit.ltim.web.quiz.entities.Attempt;

import java.util.List;
import java.util.Map;

public interface AttemptService {
    Attempt startAttempt(Long userId, Long testId);
    void submitAnswer(Long attemptId, Long questionId, String userAnswer);
    int endAttempt(Long attemptId);
    List<Map<String, Object>> getAttemptReview(Long attemptId);
    List<Long> getAttemptedTestsByUserId(Long userId);
}
