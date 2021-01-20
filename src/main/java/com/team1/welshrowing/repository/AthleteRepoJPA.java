package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import com.team1.welshrowing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AthleteRepoJPA extends JpaRepository<Athlete, Long> {

    Optional<Athlete> findById(Long id);
    Optional<Athlete> findFirstByUser(User user);

}
