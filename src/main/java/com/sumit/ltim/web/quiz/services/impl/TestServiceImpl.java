package com.sumit.ltim.web.quiz.services.impl;

import com.sumit.ltim.web.quiz.entities.Question;
import com.sumit.ltim.web.quiz.entities.Test;
import com.sumit.ltim.web.quiz.exceptions.TestNotFoundException;
import com.sumit.ltim.web.quiz.repositories.QuestionRepository;
import com.sumit.ltim.web.quiz.repositories.TestRepository;
import com.sumit.ltim.web.quiz.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Test createTest(Test test) {
        // Fetch questions from the database
        List<Question> managedQuestions = test.getQuestions().stream()
                .map(question -> questionRepository.findById(question.getId())
                        .orElseThrow(() -> new RuntimeException("Question not found with ID: " + question.getId())))
                .toList();

        // Set managed questions to the test
        test.setQuestions(managedQuestions);

        // Save the test
        return testRepository.save(test);
    }

    @Override
    public Test getTestById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found with ID: " + id));
    }

    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    @Override
    public void deleteTest(Long id) {
        if (testRepository.existsById(id)) {
            testRepository.deleteById(id);
        } else {
            throw new RuntimeException("Test not found with ID: " + id);
        }
    }
}