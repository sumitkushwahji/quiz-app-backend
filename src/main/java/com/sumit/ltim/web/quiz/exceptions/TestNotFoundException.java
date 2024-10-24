package com.sumit.ltim.web.quiz.exceptions;

public class TestNotFoundException extends RuntimeException {

    public TestNotFoundException(Long testId) {
        super("Test not found with ID: " + testId);
    }
}
