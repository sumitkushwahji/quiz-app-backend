package com.sumit.ltim.web.quiz.services.impl;

import com.sumit.ltim.web.quiz.entities.Leaderboard;
import com.sumit.ltim.web.quiz.repositories.LeaderboardRepository;
import com.sumit.ltim.web.quiz.services.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Override
    public List<Leaderboard> getTopRankers(int limit) {
        Pageable pageable = PageRequest.of(0, limit); // First page with 'limit' elements
        return leaderboardRepository.findTopRankers(pageable).getContent();
    }


    @Override
    public Leaderboard updateLeaderboard(Long userId, int score) {
        Leaderboard leaderboard = leaderboardRepository.findByUserId(userId);
        if (leaderboard != null) {
            leaderboard.setTotalScore(leaderboard.getTotalScore() + score);
            return leaderboardRepository.save(leaderboard);
        } else {
            throw new RuntimeException("User not found in leaderboard!");
        }
    }
}
