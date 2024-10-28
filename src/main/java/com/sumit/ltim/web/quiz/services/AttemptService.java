package com.sumit.ltim.web.quiz.services;

import com.sumit.ltim.web.quiz.entities.Attempt;

import java.util.List;

public interface AttemptService {
    Attempt startAttempt(Attempt attempt);
    List<Attempt> getAttemptsByTestId(Long testId);
    Attempt submitAttempt(Long id, Attempt attemptDetails);
    Attempt getAttemptById(Long id);
}
