package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.Interview;
import com.team1.welshrowing.repository.ApplicantRepo;
import com.team1.welshrowing.repository.InterviewRepo;
import com.team1.welshrowing.repository.InterviewRepoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewReadService {

    private final InterviewRepo repository;

    @Autowired
    public InterviewReadService(InterviewRepo repository) {
        this.repository = repository;
    }

    public Optional<Interview> findByApplicantId(Long id) {
        return repository.findByApplicantId(id);
    }

}
