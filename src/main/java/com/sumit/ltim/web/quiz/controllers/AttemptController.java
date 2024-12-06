package com.sumit.ltim.web.quiz.controllers;

import com.sumit.ltim.web.quiz.entities.Attempt;
import com.sumit.ltim.web.quiz.services.AttemptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attempts")
public class AttemptController {

    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping("/start")
    public ResponseEntity<Attempt> startAttempt(@RequestParam Long userId, @RequestParam Long testId) {
        Attempt attempt = attemptService.startAttempt(userId, testId);
        return ResponseEntity.ok(attempt);
    }

    @PostMapping("/{attemptId}/answers")
    public ResponseEntity<Map<String, String>> submitAnswer(
            @PathVariable Long attemptId,
            @RequestParam Long questionId,
            @RequestParam String userAnswer) {
        attemptService.submitAnswer(attemptId, questionId, userAnswer);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Answer saved successfully");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/{attemptId}/end")
    public ResponseEntity<Integer> endAttempt(@PathVariable Long attemptId) {
        int score = attemptService.endAttempt(attemptId);
        return ResponseEntity.ok(score);
    }

    @GetMapping("/{attemptId}/review")
    public ResponseEntity<List<Map<String, Object>>> getAttemptReview(@PathVariable Long attemptId) {
        List<Map<String, Object>> review = attemptService.getAttemptReview(attemptId);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/user/{userId}/attempted-tests")
    public ResponseEntity<List<Long>> getAttemptedTests(@PathVariable Long userId) {
        List<Long> attemptedTestIds = attemptService.getAttemptedTestsByUserId(userId);
        return ResponseEntity.ok(attemptedTestIds);
    }

}
