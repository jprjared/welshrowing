package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


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
    public List<Applicant> ApplicantFindByStatus(String aStatus) {
        return repository.findByStatus(aStatus);
    }
}
