package com.team1.welshrowing.repository;


import com.team1.welshrowing.domain.Athlete;
import com.team1.welshrowing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class AthleteRepoImpl implements AthleteRepo {

    private final AthleteRepoJPA repository;

    @Autowired
    public AthleteRepoImpl(AthleteRepoJPA repository) { this.repository = repository; }

    @Override
    public void saveAthlete(Athlete athlete) {
        repository.save(athlete);
    }

    public Optional<Athlete> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Athlete> findByUser(User user) {
        return repository.findFirstByUser(user);
    }


}
