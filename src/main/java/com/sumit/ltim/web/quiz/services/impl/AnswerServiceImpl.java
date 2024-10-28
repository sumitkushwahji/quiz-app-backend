package com.sumit.ltim.web.quiz.services.impl;

import com.sumit.ltim.web.quiz.entities.Answer;
import com.sumit.ltim.web.quiz.entities.Attempt;

import com.sumit.ltim.web.quiz.repositories.AnswerRepository;
import com.sumit.ltim.web.quiz.repositories.AttemptRepository;
import com.sumit.ltim.web.quiz.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AttemptRepository attemptRepository;

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public List<Answer> getAnswersByAttemptId(Long attemptId) {
        Attempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found with id " + attemptId));
        return answerRepository.findByAttempt(attempt);
    }
}