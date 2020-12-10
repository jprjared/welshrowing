package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.User;

import java.util.List;
import java.util.Optional;

public interface ApplicantRepo {
    void saveApplicant(Applicant applicant);
    void updateApplicantStatus(Applicant applicant, String status);
    void updateApplicantComments(Applicant applicant, String comments);
    void ApplicantUpdateByStatus(String newStatus,String oldStatus, Long applicantID);
    void ApplicantUpdateByComments(String newComments,String oldComments, Long applicantID);
    List<Applicant> ApplicantFindByStatus(String application_situation);
    Optional<Applicant> findById(Long Id);
    void sendEmailStatus(Applicant applicant);
}
