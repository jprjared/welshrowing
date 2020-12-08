package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.PhysicalTest;
import com.team1.welshrowing.repository.PhysicalTestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhysicalTestReadService {

    private final PhysicalTestRepo repository;

    @Autowired
    public PhysicalTestReadService(PhysicalTestRepo repository) {
        this.repository = repository;
    }

    public Optional<PhysicalTest> findByApplicantId(Long id) {
        return repository.findByApplicantId(id);
    }

}
