package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MorningMonitoringRepoJPA extends JpaRepository<MorningMonitoring, Long> {
    List<MorningMonitoring> findByUser(User user);
}
