package com.sumit.ltim.web.quiz.services;

import com.sumit.ltim.web.quiz.entities.Question;
import com.sumit.ltim.web.quiz.entities.QuestionType;

import java.util.List;

public interface QuestionService {
    Question saveQuestion(Question question);
    Question getQuestionById(Long id);
    List<Question> getQuestionsByTestId(Long testId);
    List<Question> getAllQuestions();
    List<Question> getFilteredQuestions(String subject, String topic, String exam, String difficulty, QuestionType questionType);
    Question updateQuestion(Long id, Question question);
    void deleteQuestion(Long id);
    List<String> getAllUniqueSubjects();
    List<String> getUniqueTopics();
    List<String> getAllUniqueExams();
    List<Question> getQuestionsBySubject(String subject);


    // Filter questions by various parameters
    List<Question> filterQuestions(String subject, String topic, String exam, String difficulty, QuestionType questionType);

    // Retrieve a random set of questions
    List<Question> getRandomQuestions(String subject, String topic, String exam, int numberOfQuestions);

}
