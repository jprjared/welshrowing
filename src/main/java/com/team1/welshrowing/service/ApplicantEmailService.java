package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.ApplicantRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicantEmailService {

    private final ApplicantRepo repository;

    public ApplicantEmailService(ApplicantRepo repository) {
        this.repository = repository;
    }

    public void sendApplicantEmailStatus(Applicant applicant, String emailFrom) {
        repository.sendEmailStatus(applicant,emailFrom);
    }
}
