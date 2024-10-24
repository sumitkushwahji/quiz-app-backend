package com.sumit.ltim.web.quiz.services;

import com.sumit.ltim.web.quiz.entities.Question;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question question);
    List<Question> getAllQuestions();
    Question updateQuestion(Long id, Question question);
    void deleteQuestion(Long id);
}
