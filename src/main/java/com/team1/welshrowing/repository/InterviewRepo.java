package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Interview;

import java.util.Optional;

public interface InterviewRepo {
    Optional<Interview> findByApplicantId(Long Id);
}
