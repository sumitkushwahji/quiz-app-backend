package com.sumit.ltim.web.quiz.services;


import com.sumit.ltim.web.quiz.entities.Test;

import java.util.List;

public interface TestService {
    Test saveTest(Test test);
    Test getTestById(Long id);
    List<Test> getAllTests();
    Test updateTest(Long id, Test test);
    void deleteTest(Long id);
}
