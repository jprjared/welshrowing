package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.User;

import java.util.List;

public interface MorningMonitoringRepo {
    void saveMorningMonitoring(MorningMonitoring morningMonitoring);
    List<MorningMonitoring> findByUser(User user);
}
