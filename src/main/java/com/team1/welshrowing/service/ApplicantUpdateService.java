package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.repository.ApplicantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantUpdateService {

    private final ApplicantRepo repository;

    @Autowired
    public ApplicantUpdateService(ApplicantRepo repository) {
        this.repository = repository;
    }

    public void updateApplicant(Applicant applicant) {
        repository.updateApplicant(applicant);
    }

    public List<Applicant> updateByStatus(String oldStatus, String newStatus) {
        return repository.ApplicantUpdateByStatus(oldStatus, newStatus);
    }
}
