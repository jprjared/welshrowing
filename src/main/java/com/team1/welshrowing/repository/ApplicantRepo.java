package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;

import java.util.List;

public interface ApplicantRepo {
    void saveApplicant(Applicant applicant);

    List<Applicant> ApplicantFindByStatus(String application_situation);
}

