package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
