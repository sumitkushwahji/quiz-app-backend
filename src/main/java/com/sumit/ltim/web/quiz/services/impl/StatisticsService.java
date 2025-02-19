package com.sumit.ltim.web.quiz.services.impl;

import com.sumit.ltim.web.quiz.repositories.AttemptRepository;
import com.sumit.ltim.web.quiz.repositories.QuestionRepository;
import com.sumit.ltim.web.quiz.repositories.TestRepository;
import com.sumit.ltim.web.quiz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private AttemptRepository attemptRepository;

    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("userCount", userRepository.count());
        stats.put("questionCount", questionRepository.count());
        stats.put("testCount", testRepository.count());
        stats.put("totalAttempts", attemptRepository.count());
        stats.put("completedAttempts", attemptRepository.countByIsCompleted(true));

        // Additional stats
        stats.put("averageTestScore", attemptRepository.calculateAverageScore());
        stats.put("questionsPerTest", questionRepository.calculateQuestionsPerTest());
        stats.put("activeUsers", userRepository.countActiveUsers());

        return stats;
    }
}
