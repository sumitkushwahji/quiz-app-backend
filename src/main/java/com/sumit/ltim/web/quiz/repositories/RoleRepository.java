package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.models.ERole;
import com.sumit.ltim.web.quiz.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}