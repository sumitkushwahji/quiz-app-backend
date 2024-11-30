package com.sumit.ltim.web.quiz.controllers;

import com.sumit.ltim.web.quiz.entities.Test;
import com.sumit.ltim.web.quiz.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    // Create a new test
    @PostMapping("/create")
    public Test createTest(@RequestBody Test test) {
        return testService.createTest(test);
    }

    // Get a test by ID
    @GetMapping("/{id}")
    public Test getTestById(@PathVariable Long id) {
        return testService.getTestById(id);
    }

    // Get all tests
    @GetMapping
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    // Delete a test by ID
    @DeleteMapping("/{id}")
    public String deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
        return "Test with ID " + id + " has been deleted.";
    }
}