package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.PersonalityInterview;
import com.team1.welshrowing.repository.PersonalityInterviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonalityInterviewReadService {

    private final PersonalityInterviewRepo repository;

    @Autowired
    public PersonalityInterviewReadService(PersonalityInterviewRepo repository) {
        this.repository = repository;
    }

    public Optional<PersonalityInterview> findByApplicantId(Long id) {
        return repository.findByApplicantId(id);
    }

}
