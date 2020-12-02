package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.PersonalityInterview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityInterviewRepoJPA extends JpaRepository<PersonalityInterview, Long> {
}
