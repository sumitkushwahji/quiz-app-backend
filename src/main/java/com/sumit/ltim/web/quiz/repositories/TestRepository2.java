package com.sumit.ltim.web.quiz.repositories;


import com.sumit.ltim.web.quiz.entities.Test2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository2 extends JpaRepository<Test2, Long> {
}
