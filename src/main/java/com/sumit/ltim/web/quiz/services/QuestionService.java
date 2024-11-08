package com.sumit.ltim.web.quiz.services;

import com.sumit.ltim.web.quiz.entities.Question;
import com.sumit.ltim.web.quiz.entities.QuestionType;

import java.util.List;

public interface QuestionService {
    Question saveQuestion(Question question);
    Question getQuestionById(Long id);
    List<Question> getAllQuestions();
    List<Question> getFilteredQuestions(String subject, String topic, String exam, QuestionType questionType);
    Question updateQuestion(Long id, Question question);
    void deleteQuestion(Long id);

    List<String> getAllUniqueSubjects();
    List<String> getUniqueTopics();
    List<String> getAllUniqueExams();

    List<Question> getQuestionsBySubject(String subject);
}
