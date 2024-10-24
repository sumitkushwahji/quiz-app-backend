package com.sumit.ltim.web.quiz.services.impl;

import com.sumit.ltim.web.quiz.entities.Test;
import com.sumit.ltim.web.quiz.exceptions.TestNotFoundException;
import com.sumit.ltim.web.quiz.repositories.TestRepository;
import com.sumit.ltim.web.quiz.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    @Override
    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Test getTest(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(id));
    }

    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    @Override
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    public void updateTest(Test test) {
        if (testRepository.existsById(test.getId())) {
            testRepository.save(test);
        } else {
            throw new TestNotFoundException(test.getId());
        }
    }
}
