package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT COUNT(DISTINCT a.user.id) FROM Attempt a")
    Long countActiveUsers();
}