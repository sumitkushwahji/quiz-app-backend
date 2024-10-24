package com.sumit.ltim.web.quiz.services.impl;

import com.sumit.ltim.web.quiz.entities.Option;
import com.sumit.ltim.web.quiz.entities.Question;
import com.sumit.ltim.web.quiz.repositories.QuestionRepository;
import com.sumit.ltim.web.quiz.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        // Set the question reference in each option
        for (Option option : question.getOptions()) {
            option.setQuestion(question);
        }
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
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
}
