package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.PersonalityInterview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalityInterviewRepoJPA extends JpaRepository<PersonalityInterview, Long> {
    Optional<PersonalityInterview> findByApplicantId(Long Id);
}
