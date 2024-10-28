package com.sumit.ltim.web.quiz.controllers;

import com.sumit.ltim.web.quiz.entities.Attempt;

import com.sumit.ltim.web.quiz.services.AttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attempts")
public class AttemptController {

    @Autowired
    private AttemptService attemptService;

    @PostMapping
    public ResponseEntity<Attempt> startAttempt(@RequestBody Attempt attempt) {
        return ResponseEntity.ok(attemptService.startAttempt(attempt));
    }

    @GetMapping("/{testId}")
    public ResponseEntity<List<Attempt>> getAttemptsByTestId(@PathVariable Long testId) {
        return ResponseEntity.ok(attemptService.getAttemptsByTestId(testId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attempt> submitAttempt(@PathVariable Long id, @RequestBody Attempt attemptDetails) {
        return ResponseEntity.ok(attemptService.submitAttempt(id, attemptDetails));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Attempt> getAttemptById(@PathVariable Long id) {
        return ResponseEntity.ok(attemptService.getAttemptById(id));
    }
}