package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.RPE;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RPERepoJPA extends JpaRepository<RPE, Long> {
    List<RPE> findByUser(User user);

    Optional<RPE> findTopByUserOrderByDateofTestDesc (User user);
}
