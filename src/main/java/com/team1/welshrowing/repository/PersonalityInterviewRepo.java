package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.PersonalityInterview;

import java.util.Optional;

public interface PersonalityInterviewRepo {
    Optional<PersonalityInterview> findByApplicantId(Long Id);
}
