package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.User;

import java.util.List;
import java.util.Optional;

public interface MorningMonitoringRepo {
    void saveMorningMonitoring(MorningMonitoring morningMonitoring);
    List<MorningMonitoring> findByUser(User user);
    Optional<MorningMonitoring> findLatestByUser(User user);
}
