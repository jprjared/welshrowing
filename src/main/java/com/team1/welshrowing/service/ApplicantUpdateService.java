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

    public void updateApplicantStatus(Applicant applicant, String status) {
        repository.updateApplicantStatus(applicant, status);
    }

    public void updateApplicantComments(Applicant applicant, String comments) {
        repository.updateApplicantComments(applicant, comments);
    }
    
    public void updateByStatus(String newStatus, String oldStatus, Long applicantID) {
        repository.ApplicantUpdateByStatus(newStatus, oldStatus, applicantID);
    }


}
