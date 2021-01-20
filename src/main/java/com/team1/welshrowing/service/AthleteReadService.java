package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.Athlete;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.AthleteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AthleteReadService {

    private final AthleteRepo repository;

    @Autowired
    public AthleteReadService(AthleteRepo repository) {this.repository = repository;}

    public Optional<Athlete> findById(Long id) { return repository.findById(id); }

    public Optional<Athlete> findByUser(User user) { return repository.findByUser(user); }
}
