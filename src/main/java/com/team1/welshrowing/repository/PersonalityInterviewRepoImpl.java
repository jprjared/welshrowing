package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.PersonalityInterview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonalityInterviewRepoImpl implements PersonalityInterviewRepo {

    private final PersonalityInterviewRepoJPA repository;

    @Autowired
    public PersonalityInterviewRepoImpl(PersonalityInterviewRepoJPA repository) { this.repository = repository; }

    @Override
    public Optional<PersonalityInterview> findByApplicantId(Long id) {
        return repository.findByApplicantId(id);
    }
}
