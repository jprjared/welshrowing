package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.Feedback;
import com.team1.welshrowing.domain.User;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface ApplicantRepo {
    void saveApplicant(Applicant applicant);
    void updateApplicantStatus(Applicant applicant, String status);
    void updateApplicantComments(Applicant applicant, String comments);
    void ApplicantUpdateByStatus(String newStatus, Long applicantID);

    List<Applicant> ApplicantFindByStatus(String application_situation);
    Optional<Applicant> findById(Long Id);
    Optional<Applicant> findByUser(User user);
    void sendEmailStatus(String status, Applicant applicant);

    void sendEmailPassFail(String status, Applicant applicant);

    void sendEmailFeedback(Applicant applicant, String feedback, String file);

}
