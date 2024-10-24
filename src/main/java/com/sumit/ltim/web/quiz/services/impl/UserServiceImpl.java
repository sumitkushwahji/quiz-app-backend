package com.sumit.ltim.web.quiz.services.impl;

import com.sumit.ltim.web.quiz.entities.User;
import com.sumit.ltim.web.quiz.entities.UserAchievement;
import com.sumit.ltim.web.quiz.entities.UserTestProgress;
import com.sumit.ltim.web.quiz.repositories.UserRepository;
import com.sumit.ltim.web.quiz.repositories.UserTestProgressRepository;
import com.sumit.ltim.web.quiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTestProgressRepository userTestProgressRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public User loginUser(String username, String password) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null && existingUser.getPassword().equals(password)) {
            return existingUser;
        }
        return null;  // Invalid credentials
    }

    @Override
    public UserTestProgress saveTestProgress(UserTestProgress progress) {
        return userTestProgressRepository.save(progress);
    }

    @Override
    public List<UserAchievement> getUserAchievements(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"))
                .getAchievements();
    }
}
