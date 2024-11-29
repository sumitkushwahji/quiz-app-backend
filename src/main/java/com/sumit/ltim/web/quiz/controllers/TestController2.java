package com.sumit.ltim.web.quiz.controllers;


import com.sumit.ltim.web.quiz.entities.Test2;

import com.sumit.ltim.web.quiz.services.TestService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/tests2")
public class TestController2 {

    @Autowired
    private TestService2 testService2;

    @PostMapping("/dynamic")
    public ResponseEntity<Test2> createDynamicTest(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String topic,
            @RequestParam(required = false) String exam,
            @RequestParam(required = false) String difficulty,
            @RequestParam(defaultValue = "10") int questionCount,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) LocalDateTime startTime,
            @RequestParam(required = false) LocalDateTime endTime
    ) {
        Test2 test2 = testService2.createDynamicTest(name, description, subject, topic, exam, difficulty, questionCount, duration, startTime, endTime);
        return ResponseEntity.ok(test2);
    }
}
