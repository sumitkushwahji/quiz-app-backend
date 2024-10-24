package com.sumit.ltim.web.quiz.services;


import com.sumit.ltim.web.quiz.entities.Test;

import java.util.List;

public interface TestService {

    Test createTest(Test test);

    Test getTest(Long id);

    List<Test> getAllTests();

    void deleteTest(Long id);

    void updateTest(Test test);
}
