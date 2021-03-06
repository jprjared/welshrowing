package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.PhysicalTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhysicalTestRepoJPA extends JpaRepository<PhysicalTest, Long> {
    Optional<PhysicalTest> findByApplicantId(Long Id);
}
