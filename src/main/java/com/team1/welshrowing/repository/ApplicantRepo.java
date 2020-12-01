package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;
import java.util.List;
import java.util.Optional;

public interface ApplicantRepo {
    void saveApplicant(Applicant applicant);
    List<Applicant> ApplicantFindByStatus(String application_situation);
    Optional<Applicant> findById(Long id);
}
