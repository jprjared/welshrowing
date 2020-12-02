package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;

import java.util.List;

public interface ApplicantRepo {

    void saveApplicant(Applicant applicant);
    void updateApplicant(Applicant applicant);

    List<Applicant> ApplicantUpdateByStatus(String oldStatus, String newStatus);

    List<Applicant> ApplicantFindByStatus(String application_situation);
}

