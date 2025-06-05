package com.sumit.ltim.web.quiz.controllers;

import com.sumit.ltim.web.quiz.entities.Option;
import com.sumit.ltim.web.quiz.entities.Question;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

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
        Question currentQuestion = null;

        String[] lines = text.split("\n"); // Split the PDF text by lines
        boolean isProcessingOptions = false;

        for (String line : lines) {
            line = line.trim();

            // Check if the line starts a new question (e.g., "1. What is Java?")
            if (line.matches("^\\d+\\.\\s+.*")) {
                if (currentQuestion != null) {
                    questions.add(currentQuestion); // Save previous question
                }
                currentQuestion = new Question();
                currentQuestion.setText(line.replaceFirst("^\\d+\\.\\s+", "")); // Remove question number
                currentQuestion.setOptions(new ArrayList<>());
                isProcessingOptions = false; // Reset option tracking
            }
            // Check if the line starts an option (e.g., "(A) Option text")
            else if (line.matches("^\\([A-D]\\)\\s+.*")) {
                isProcessingOptions = true; // Now we're processing options
                if (currentQuestion != null) {
                    Option option = new Option();
                    option.setText(line.substring(3).trim()); // Remove "(A) "
                    currentQuestion.getOptions().add(option);
                }
            }
            // Multi-line options (if the previous line was an option, continue adding text)
            else if (isProcessingOptions && !line.isEmpty()) {
                List<Option> options = currentQuestion.getOptions();
                if (!options.isEmpty()) {
                    Option lastOption = options.get(options.size() - 1);
                    lastOption.setText(lastOption.getText() + " " + line); // Append multi-line option text
                }
            }
            // Multi-line question continuation (if it's not an option)
            else if (!line.isEmpty() && currentQuestion != null) {
                currentQuestion.setText(currentQuestion.getText() + " " + line);
            }
        }

        if (currentQuestion != null) {
            questions.add(currentQuestion); // Save last question
        }

        return questions;
    }



}

