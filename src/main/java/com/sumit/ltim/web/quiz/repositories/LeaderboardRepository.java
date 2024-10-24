package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.entities.Leaderboard;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    // Custom JPQL query to find top rankers
    // Custom JPQL query to find top rankers with pagination support
    @Query(value = "SELECT l FROM Leaderboard l ORDER BY l.totalScore DESC", countQuery = "SELECT count(l) FROM Leaderboard l")
    Page<Leaderboard> findTopRankers(Pageable pageable);


    // Use derived query method by convention
    Leaderboard findByUserId(Long userId);

    // Alternatively, you can write a custom query if needed
    @Query("SELECT l FROM Leaderboard l WHERE l.user.id = :userId")
    Leaderboard findLeaderboardByUserId(Long userId);
}
