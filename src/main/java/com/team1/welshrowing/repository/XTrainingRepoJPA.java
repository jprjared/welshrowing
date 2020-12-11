package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface XTrainingRepoJPA extends JpaRepository<XTraining, Long> {
    List<XTraining> findByUser(User user);
}
