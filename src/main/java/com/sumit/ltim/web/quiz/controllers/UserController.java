package com.sumit.ltim.web.quiz.controllers;

import com.sumit.ltim.web.quiz.entities.User;
import com.sumit.ltim.web.quiz.entities.UserTestProgress;
import com.sumit.ltim.web.quiz.entities.UserAchievement;
import com.sumit.ltim.web.quiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // User registration
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        User registeredUser = userService.createUser(user);
        if (registeredUser != null) {
            return ResponseEntity.ok("User registered successfully.");
        }
        return ResponseEntity.badRequest().body("User registration failed.");
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            return ResponseEntity.ok("Login successful.");
        }
        return ResponseEntity.status(401).body("Invalid username or password.");
    }

    // Get user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    // Save test progress
    @PostMapping("/progress")
    public UserTestProgress saveTestProgress(@RequestBody UserTestProgress progress) {
        return userService.saveTestProgress(progress);
    }

    // Get user achievements
    @GetMapping("/{userId}/achievements")
    public List<UserAchievement> getUserAchievements(@PathVariable Long userId) {
        return userService.getUserAchievements(userId);
    }
}
