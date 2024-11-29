package com.sumit.ltim.web.quiz.services.impl;

import com.sumit.ltim.web.quiz.entities.Question;
import com.sumit.ltim.web.quiz.entities.Test;
import com.sumit.ltim.web.quiz.exceptions.TestNotFoundException;
import com.sumit.ltim.web.quiz.repositories.QuestionRepository;
import com.sumit.ltim.web.quiz.repositories.TestRepository;
import com.sumit.ltim.web.quiz.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;



    @Autowired
    public TestServiceImpl(TestRepository testRepository, QuestionRepository questionRepository) {
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Test getTestById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException("Test not found with id " + id));
    }

    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    @Override
    public Test updateTest(Long id, Test test) {
        Test existingTest = getTestById(id);
        existingTest.setTestName(test.getTestName());
        existingTest.setDescription(test.getDescription());
        existingTest.setDuration(test.getDuration());
        existingTest.setQuestions(test.getQuestions());
        return testRepository.save(existingTest);
    }

    @Override
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }

    public Test createTestWithRandomQuestions(String subject, String topic, String exam, int numberOfQuestions) {
        List<Question> randomQuestions = questionRepository.findRandomQuestions(subject, topic, exam, numberOfQuestions);
        Test test = new Test();
        test.setTestName("Randomized Test");
        test.setDescription("Test generated with random questions");
        test.setQuestions(randomQuestions);
        return testRepository.save(test);
    }
}