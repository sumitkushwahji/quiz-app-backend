package com.sumit.ltim.web.quiz.services.impl;

import com.sumit.ltim.web.quiz.entities.*;
import com.sumit.ltim.web.quiz.models.User;
import com.sumit.ltim.web.quiz.repositories.*;
import com.sumit.ltim.web.quiz.services.AttemptService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AttemptServiceImpl implements AttemptService {

    private final AttemptRepository attemptRepository;
    private final AnswerRepository answerRepository;
    private final TestRepository testRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public AttemptServiceImpl(
            AttemptRepository attemptRepository,
            AnswerRepository answerRepository,
            TestRepository testRepository,
            UserRepository userRepository,
            @Lazy QuestionRepository questionRepository) {
        this.attemptRepository = attemptRepository;
        this.answerRepository = answerRepository;
        this.testRepository = testRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    @Transactional
    public Attempt startAttempt(Long userId, Long testId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new IllegalArgumentException("Test not found"));

        if (attemptRepository.findByUserIdAndTestId(userId, testId).isPresent()) {
            throw new IllegalStateException("Attempt already exists for this test");
        }

        Attempt attempt = new Attempt();
        attempt.setUser(user);
        attempt.setTest(test);
        attempt.setStartTime(LocalDateTime.now());
        attempt = attemptRepository.save(attempt);

        // Initialize answers
        for (Question question : test.getQuestions()) {
            Answer answer = new Answer();
            answer.setAttempt(attempt);
            answer.setQuestion(question);
            answer.setUserAnswer(null); // Ensure userAnswer is initially null
            answerRepository.save(answer);
        }

        return attempt;
    }

    @Override
    @Transactional
    public void submitAnswer(Long attemptId, Long questionId, String userAnswer) {
        Answer answer = answerRepository.findByAttemptIdAndQuestionId(attemptId, questionId);

        if (answer == null) {
            throw new IllegalStateException("Answer not initialized for this question");
        }

        // Update the userAnswer for the question
        answer.setUserAnswer(userAnswer);
        answerRepository.save(answer);

        System.out.println("Answer submitted: " + answer);
    }



    @Override
    @Transactional
    public int endAttempt(Long attemptId) {
        Attempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new IllegalArgumentException("Attempt not found"));

        if (attempt.getIsCompleted()) {
            return attempt.getScore(); // Return the existing score
        }

        List<Answer> answers = answerRepository.findByAttemptId(attemptId);
        int score = calculateScore(answers); // Check if this method is correctly called

        attempt.setScore(score);
        attempt.setEndTime(LocalDateTime.now());
        attempt.setIsCompleted(true);
        attemptRepository.save(attempt);

        return score;
    }


    private int calculateScore(List<Answer> answers) {
        System.out.println("Calculating score for answers: " + answers);

        return (int) answers.stream()
                .filter(answer -> {
                    String userAnswer = answer.getUserAnswer();
                    System.out.println("User Answer: " + userAnswer);

                    // Skip if userAnswer is null or empty
                    if (userAnswer == null || userAnswer.trim().isEmpty()) {
                        System.out.println("User answer is empty or null, skipping this answer.");
                        return false;
                    }

                    try {
                        Long userAnswerId = Long.parseLong(userAnswer.trim());
                        System.out.println("Parsed User Answer ID: " + userAnswerId);

                        // Check if the user-selected option is correct
                        boolean isCorrect = answer.getQuestion()
                                .getOptions()
                                .stream()
                                .anyMatch(option -> {
                                    boolean match = option.getId() == userAnswerId && Boolean.TRUE.equals(option.getIsCorrect());
                                    System.out.println("Checking Option: " + option.getId() + " Is Correct: " + option.getIsCorrect() + " Match: " + match);
                                    return match;
                                });


                        System.out.println("Is Correct: " + isCorrect);
                        return isCorrect;
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid userAnswer: " + userAnswer);
                        return false;
                    }
                })
                .count(); // Count the correct answers
    }



    @Override
    public List<Map<String, Object>> getAttemptReview(Long attemptId) {
        Attempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new IllegalArgumentException("Attempt not found"));

        return attempt.getTest().getQuestions().stream().map(question -> {
            Answer answer = answerRepository.findByAttemptIdAndQuestionId(attemptId, question.getId());
            String userAnswer = answer != null ? answer.getUserAnswer() : null;

            Option correctOption = question.getOptions().stream()
                    .filter(Option::getIsCorrect)
                    .findFirst()
                    .orElse(null);

            Map<String, Object> reviewData = new HashMap<>();
            reviewData.put("questionId", question.getId());
            reviewData.put("questionText", question.getText());
            reviewData.put("options", question.getOptions());
            reviewData.put("userAnswer", userAnswer);
            reviewData.put("correctAnswer", correctOption != null ? correctOption.getId() : null);
            reviewData.put("explanation", question.getExplanation());

            return reviewData;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Long> getAttemptedTestsByUserId(Long userId) {
        return attemptRepository.findAllByUserId(userId)
                .stream()
                .map(attempt -> attempt.getTest().getId())
                .distinct()
                .collect(Collectors.toList());
    }
}
