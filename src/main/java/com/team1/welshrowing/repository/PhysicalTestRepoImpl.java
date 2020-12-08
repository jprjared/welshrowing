package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.PhysicalTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PhysicalTestRepoImpl implements PhysicalTestRepo {

    private final PhysicalTestRepoJPA repository;

    @Autowired
    public PhysicalTestRepoImpl(PhysicalTestRepoJPA repository) { this.repository = repository; }

    @Override
    public Optional<PhysicalTest> findByApplicantId(Long id) {
        return repository.findByApplicantId(id);
    }
}
