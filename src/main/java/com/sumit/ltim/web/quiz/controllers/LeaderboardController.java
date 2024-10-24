package com.sumit.ltim.web.quiz.controllers;

import com.sumit.ltim.web.quiz.entities.Leaderboard;
import com.sumit.ltim.web.quiz.services.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping("/top/{limit}")
    public List<Leaderboard> getTopRankers(@PathVariable int limit) {
        return leaderboardService.getTopRankers(limit);
    }

    @PostMapping("/update")
    public Leaderboard updateLeaderboard(@RequestParam Long userId, @RequestParam int score) {
        return leaderboardService.updateLeaderboard(userId, score);
    }
}
