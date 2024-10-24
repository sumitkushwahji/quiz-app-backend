package com.sumit.ltim.web.quiz.services;

import com.sumit.ltim.web.quiz.entities.Leaderboard;

import java.util.List;

public interface LeaderboardService {

    List<Leaderboard> getTopRankers(int limit);

    Leaderboard updateLeaderboard(Long userId, int score);
}