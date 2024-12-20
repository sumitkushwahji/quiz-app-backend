package com.sumit.ltim.web.quiz.services.impl;

import com.sumit.ltim.web.quiz.entities.Option;
import com.sumit.ltim.web.quiz.entities.Question;
import com.sumit.ltim.web.quiz.entities.QuestionType;
import com.sumit.ltim.web.quiz.exceptions.QuestionNotFoundException;
import com.sumit.ltim.web.quiz.repositories.QuestionRepository;
import com.sumit.ltim.web.quiz.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

//    @Override
//    public Question saveQuestion(Question question) {
//        return questionRepository.save(question);
//    }
    @Override
    public Question saveQuestion(Question question) {
        for (Option option : question.getOptions()) {
            option.setQuestion(question);
        }
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found with id " + id));

    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }


    @Override
    public List<Question> getFilteredQuestions(String subject, String topic, String exam, String difficulty, QuestionType questionType) {
        return questionRepository.findByFilters(subject, topic, exam, difficulty, questionType);
    }

    @Override
    public List<Question> filterQuestions(String subject, String topic, String exam, String difficulty, QuestionType questionType) {
        return questionRepository.findByFilters(subject, topic, exam, difficulty, questionType);
    }

    @Override
    public List<Question> getRandomQuestions(String subject, String topic, String exam, int numberOfQuestions) {
        return questionRepository.findRandomQuestions(subject, topic, exam, numberOfQuestions);
    }

    public List<Question> getQuestionsByTestId(Long testId) {
        return questionRepository.findByTests_Id(testId);  // Fetch questions by test_id
    }

    @Override
    public Question updateQuestion(Long id, Question question) {
        Question existingQuestion = questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
        // Update the text, explanation, subject, topic, and exam fields
        existingQuestion.setText(question.getText());
        existingQuestion.setExplanation(question.getExplanation());
        existingQuestion.setSubject(question.getSubject()); // Added subject
        existingQuestion.setTopic(question.getTopic()); // Added topic
        existingQuestion.setExam(question.getExam()); // Added exam
        existingQuestion.setQuestionType(question.getQuestionType()); // Added exam
        existingQuestion.setDifficulty(question.getDifficulty()); // Added exam


        // Clear and re-add options
        existingQuestion.getOptions().clear();
        existingQuestion.getOptions().addAll(question.getOptions());
        for (Option option : existingQuestion.getOptions()) {
            option.setQuestion(existingQuestion);
        }
        return questionRepository.save(existingQuestion);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<String> getAllUniqueSubjects() {
        return questionRepository.findDistinctSubjects();
    }

    @Override
    public List<String> getUniqueTopics() {
        return questionRepository.findDistinctTopics();
    }


    @Override
    public List<String> getAllUniqueExams() {
        return questionRepository.findDistinctExams();
    }

    @Override
    // Fetch all questions by subject
    public List<Question> getQuestionsBySubject(String subject) {
        return questionRepository.findBySubject(subject);
    }
}