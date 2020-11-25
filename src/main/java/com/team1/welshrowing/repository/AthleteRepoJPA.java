package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepoJPA extends JpaRepository<Athlete, Long> {
}
