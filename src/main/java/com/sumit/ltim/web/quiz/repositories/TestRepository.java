package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {}
