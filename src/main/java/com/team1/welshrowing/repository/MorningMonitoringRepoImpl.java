package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MorningMonitoringRepoImpl implements MorningMonitoringRepo {

    private final MorningMonitoringRepoJPA repository;

    @Autowired
    public MorningMonitoringRepoImpl(MorningMonitoringRepoJPA repository) { this.repository = repository; }


    @Override
    public void saveMorningMonitoring(MorningMonitoring morningMonitoring) {
        repository.save(morningMonitoring);
    }

    @Override
    public List<MorningMonitoring> findByUser(User user) {
        return repository.findByUser(user);
    }


}
