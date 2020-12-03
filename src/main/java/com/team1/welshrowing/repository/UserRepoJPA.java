package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepoJPA extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
