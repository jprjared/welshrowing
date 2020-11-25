package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepoJPA extends JpaRepository <User, Long> {
}
