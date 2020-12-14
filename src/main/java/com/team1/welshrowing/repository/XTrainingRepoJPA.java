package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface XTrainingRepoJPA extends JpaRepository<XTraining, Long> {
    List<XTraining> findByUserOrderByDateOfTrainingDesc(User user);

    Optional<XTraining> findTopByUserOrderByDateOfTrainingDesc (User user);
}
