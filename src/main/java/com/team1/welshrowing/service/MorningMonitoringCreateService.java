package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.repository.MorningMonitoringRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MorningMonitoringCreateService {

    private final MorningMonitoringRepo repository;

    @Autowired
    public MorningMonitoringCreateService(MorningMonitoringRepo repository) {
        this.repository = repository;
    }

    public void addMorningMonitoring(MorningMonitoring morningMonitoring) {
        repository.saveMorningMonitoring(morningMonitoring);
    }


}
