package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}