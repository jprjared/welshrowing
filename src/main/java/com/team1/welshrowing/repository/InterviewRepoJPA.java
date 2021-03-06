package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewRepoJPA extends JpaRepository<Interview, Long> {
    Optional<Interview> findByApplicantId(Long Id);
}
