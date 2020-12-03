package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepoJPA extends JpaRepository<Interview, Long> {
}
