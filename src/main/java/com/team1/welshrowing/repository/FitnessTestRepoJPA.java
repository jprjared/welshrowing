package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FitnessTestRepoJPA extends JpaRepository<Applicant, Long> {
}
