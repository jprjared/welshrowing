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

    /**
     * Informs applicant about the status of the application, by sending an email
     * @param applicant - gets info from the applicant
     */
    public void sendApplicantEmailStatus(Applicant applicant) {
        repository.sendEmailStatus(applicant);
    }
}
