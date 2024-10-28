package com.sumit.ltim.web.quiz.controllers;

import com.sumit.ltim.web.quiz.entities.Question;
import com.sumit.ltim.web.quiz.entities.QuestionType;
import com.sumit.ltim.web.quiz.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }
    @GetMapping
    public ResponseEntity<List<Question>> getQuestions(
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String topic,
            @RequestParam(required = false) String exam,
            @RequestParam(required = false) QuestionType questionType
    ) {
        List<Question> questions = questionService.getFilteredQuestions(subject, topic, exam, questionType);
        return ResponseEntity.ok(questions);
    }

//    @GetMapping
//    public ResponseEntity<List<Question>> getAllQuestions() {
//        List<Question> questions = questionService.getAllQuestions();
//        return ResponseEntity.ok(questions);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        Question updatedQuestion = questionService.updateQuestion(id, question);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/unique/subjects")
    public ResponseEntity<List<String>> getAllUniqueSubjects() {
        List<String> subjects = questionService.getAllUniqueSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/unique/topics")
    public ResponseEntity<List<String>> getUniqueTopicsBySubject(@RequestParam String subject) {
        List<String> topics = questionService.getUniqueTopicsBySubject(subject);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/unique/exams")
    public ResponseEntity<List<String>> getAllUniqueExams() {
        List<String> exams = questionService.getAllUniqueExams();
        return ResponseEntity.ok(exams);
    }
}
