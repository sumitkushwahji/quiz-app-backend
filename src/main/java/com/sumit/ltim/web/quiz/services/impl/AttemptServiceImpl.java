package com.sumit.ltim.web.quiz.services.impl;

import com.sumit.ltim.web.quiz.entities.Attempt;
import com.sumit.ltim.web.quiz.entities.Test;

import com.sumit.ltim.web.quiz.repositories.AttemptRepository;
import com.sumit.ltim.web.quiz.repositories.TestRepository;
import com.sumit.ltim.web.quiz.services.AttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttemptServiceImpl implements AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    @Autowired
    private TestRepository testRepository;

    @Override
    public Attempt startAttempt(Attempt attempt) {
        return attemptRepository.save(attempt);
    }

    @Override
    public List<Attempt> getAttemptsByTestId(Long testId) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found with id " + testId));
        return attemptRepository.findByTest(test);
    }

    @Override
    public Attempt submitAttempt(Long id, Attempt attemptDetails) {
        return attemptRepository.findById(id)
                .map(attempt -> {
                    attempt.setEndTime(attemptDetails.getEndTime());
                    attempt.setScore(attemptDetails.getScore());
                    attempt.setAnswers(attemptDetails.getAnswers());
                    return attemptRepository.save(attempt);
                }).orElseThrow(() -> new RuntimeException("Attempt not found with id " + id));
    }

    @Override
    public Attempt getAttemptById(Long id) {
        return attemptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attempt not found with id " + id));
    }
}
