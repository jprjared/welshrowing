package com.team1.welshrowing.repository;


import com.team1.welshrowing.domain.Athlete;
import com.team1.welshrowing.domain.User;

import java.util.Optional;

public interface AthleteRepo {

    void saveAthlete(Athlete athlete);
    Optional<Athlete> findById(Long Id);
    Optional<Athlete> findByUser(User user);
}
