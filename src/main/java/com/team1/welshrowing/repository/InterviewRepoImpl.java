package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Interview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InterviewRepoImpl implements InterviewRepo {

    private final InterviewRepoJPA repository;

    @Autowired
    public InterviewRepoImpl(InterviewRepoJPA repository) { this.repository = repository; }

    @Override
    public Optional<Interview> findByApplicantId(Long id) {
        return repository.findByApplicantId(id);
    }
}
