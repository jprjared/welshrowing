package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.PhysicalTest;

import java.util.Optional;

public interface PhysicalTestRepo {
    Optional<PhysicalTest> findByApplicantId(Long Id);
}
