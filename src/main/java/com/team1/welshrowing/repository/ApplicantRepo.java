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
    void ApplicantUpdateByStatus(String newStatus,String oldStatus, Long applicantID);
    List<Applicant> ApplicantFindByStatus(String application_situation);
    Optional<Applicant> findById(Long Id);
    void sendEmailStatus(Applicant applicant);

    void sendEmailPassFail(Applicant applicant);

    void sendEmailFeedback(Applicant applicant, String feedback, String file);

}
