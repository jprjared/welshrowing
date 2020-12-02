package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.repository.ApplicantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantReadService {

    private final ApplicantRepo repository;

    @Autowired
    public ApplicantReadService(ApplicantRepo repository) {
        this.repository = repository;
    }

    public List<Applicant> findByStatus(String aStatus) {
        return repository.ApplicantFindByStatus(aStatus);
    }

    public Optional<Applicant> findById(Long id) { return repository.findById(id); }
}
