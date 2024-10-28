package com.sumit.ltim.web.quiz.controllers;


import com.sumit.ltim.web.quiz.entities.Answer;

import com.sumit.ltim.web.quiz.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<Answer> saveAnswer(@RequestBody Answer answer) {
        return ResponseEntity.ok(answerService.saveAnswer(answer));
    }

    @GetMapping("/{attemptId}")
    public ResponseEntity<List<Answer>> getAnswersByAttemptId(@PathVariable Long attemptId) {
        return ResponseEntity.ok(answerService.getAnswersByAttemptId(attemptId));
    }
}