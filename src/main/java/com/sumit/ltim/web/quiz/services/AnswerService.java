package com.sumit.ltim.web.quiz.services;

import com.sumit.ltim.web.quiz.entities.Answer;

import java.util.List;

public interface AnswerService {
    Answer saveAnswer(Answer answer);
    List<Answer> getAnswersByAttemptId(Long attemptId);
}