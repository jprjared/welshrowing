package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.MorningMonitoring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MorningMonitoringRepoImpl implements MorningMonitoringRepo {

    private final MorningMonitoringRepoJPA repository;

    @Autowired
    public MorningMonitoringRepoImpl(MorningMonitoringRepoJPA repository) { this.repository = repository; }


    @Override
    public void saveMorningMonitoring(MorningMonitoring morningMonitoring) {
        repository.save(morningMonitoring);
    }
}
