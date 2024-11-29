package com.sumit.ltim.web.quiz.services;


import com.sumit.ltim.web.quiz.entities.Difficulty;
import com.sumit.ltim.web.quiz.entities.Question;

import com.sumit.ltim.web.quiz.entities.Test2;
import com.sumit.ltim.web.quiz.repositories.QuestionRepository;

import com.sumit.ltim.web.quiz.repositories.TestRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class TestService2 {

    @Autowired
    private TestRepository2 testRepository2;

    @Autowired
    private QuestionRepository questionRepository;

    // Dynamically filter questions
    public List<Question> filterQuestions(String subject, String topic, String exam, String difficulty) {
        return questionRepository.findByFilters(subject, topic, exam, difficulty);
    }

    // Randomly select questions from the filtered list
    public List<Question> selectRandomQuestions(List<Question> questions, int count) {
        if (questions.size() <= count) {
            return questions;
        }
        Collections.shuffle(questions); // Shuffle to ensure randomness
        return questions.subList(0, count);
    }

    // Create a test based on dynamic filters and random selection
    public Test2 createDynamicTest(String name, String description, String subject, String topic, String exam,
                                   String difficulty, int questionCount, Integer duration,
                                   LocalDateTime startTime, LocalDateTime endTime) {
        // Filter questions based on the provided criteria
        List<Question> filteredQuestions = filterQuestions(subject, topic, exam, difficulty);

        // Select random questions from the filtered list
        List<Question> selectedQuestions = selectRandomQuestions(filteredQuestions, questionCount);

        // Create and save the test
        Test2 test2 = new Test2();
        test2.setName(name);
        test2.setDescription(description);
        test2.setSubject(subject);
        test2.setTopic(topic);
        test2.setExam(exam);
        test2.setDifficulty(difficulty != null ? Difficulty.valueOf(difficulty.toUpperCase()) : null);
        test2.setQuestions(selectedQuestions);
        test2.setQuestionCount(selectedQuestions.size());
        test2.setDuration(duration);
        test2.setStartTime(startTime);
        test2.setEndTime(endTime);

        return testRepository2.save(test2);
    }
}
