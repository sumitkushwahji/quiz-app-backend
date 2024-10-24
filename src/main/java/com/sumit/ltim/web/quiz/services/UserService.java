package com.sumit.ltim.web.quiz.services;

import com.sumit.ltim.web.quiz.entities.User;
import com.sumit.ltim.web.quiz.entities.UserAchievement;
import com.sumit.ltim.web.quiz.entities.UserTestProgress;

import java.util.List;

public interface UserService {

    User createUser(User user);  // Register user

    User getUser(Long id);  // Get user by ID

    User loginUser(String username, String password);  // Login user

    UserTestProgress saveTestProgress(UserTestProgress progress);  // Save test progress

    List<UserAchievement> getUserAchievements(Long userId);  // Get user achievements
}
