package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.repository.ApplicantRepo;
import com.team1.welshrowing.repository.ApplicantRepoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantCreateService {

    private final ApplicantRepo repository;

    @Autowired
    public ApplicantCreateService(ApplicantRepo repository) {
        this.repository = repository;
    }

    public void addApplicant(Applicant applicant) {
        repository.saveApplicant(applicant);
    }


}
