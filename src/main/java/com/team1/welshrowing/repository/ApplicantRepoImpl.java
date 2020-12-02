package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ApplicantRepoImpl implements ApplicantRepo {

    private final ApplicantRepoJPA repository;

    @Autowired
    public ApplicantRepoImpl(ApplicantRepoJPA repository) { this.repository = repository; }

    @Override
    public void saveApplicant(Applicant applicant) {
        repository.save(applicant);
    }

    @Override
    public void updateApplicant(Applicant applicant) {
        repository.save(applicant);
    }

    @Override
    public List<Applicant> ApplicantUpdateByStatus(String oldStatus, String newStatus) {
        return repository.updateStatus(oldStatus, newStatus);
    }

    @Override
    public List<Applicant> ApplicantFindByStatus(String aStatus) {
        return repository.findByStatus(aStatus);
    }
}

