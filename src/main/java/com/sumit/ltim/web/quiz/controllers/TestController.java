package com.sumit.ltim.web.quiz.controllers;

import com.sumit.ltim.web.quiz.entities.Option;
import com.sumit.ltim.web.quiz.entities.Question;
import com.sumit.ltim.web.quiz.entities.Test;
import com.sumit.ltim.web.quiz.services.TestService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
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


    @PostMapping("/files")
    public ResponseEntity<List<Question>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            File tempFile = File.createTempFile("uploaded", ".pdf");
            file.transferTo(tempFile);

            // Extract text from PDF
            PDDocument document = PDDocument.load(tempFile);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String extractedText = pdfStripper.getText(document);
            document.close();

            List<Question> questions = processText(extractedText);

            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    private List<Question> processText(String text) {
        List<Question> questions = new ArrayList<>();

        String[] lines = text.split("\n");
        Question currentQuestion = null;

        for (String line : lines) {
            line = line.trim();
            if (line.matches("^\\d+\\.\\s+.*")) {  // Detects questions (e.g., "1. What is Java?")
                if (currentQuestion != null) {
                    questions.add(currentQuestion);
                }
                currentQuestion = new Question();
                currentQuestion.setText(line.substring(line.indexOf(" ") + 1));
                currentQuestion.setOptions(new ArrayList<>());
            } else if (line.matches("^[A-D]\\)\\s+.*")) {  // Detects options (e.g., "A) OOP")
                if (currentQuestion != null) {
                    Option option = new Option();
                    option.setText(line.substring(3));
                    currentQuestion.getOptions().add(option);
                }
            }
        }

        if (currentQuestion != null) {
            questions.add(currentQuestion);
        }

        return questions;
    }
}