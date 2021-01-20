package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.Athlete;
import com.team1.welshrowing.repository.AthleteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AthleteCreateService {

    private final AthleteRepo repository;

    @Autowired
    public AthleteCreateService(AthleteRepo repository) {this.repository = repository;}

    public void addAthlete(Athlete athlete) {repository.saveAthlete(athlete);}

}
