package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.RPE;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RPERepoImpl implements RPERepo {

    private final RPERepoJPA repository;

    @Autowired
    public RPERepoImpl(RPERepoJPA repository) {this.repository = repository; }

    @Override
    public void saveRPE(RPE rpe) {repository.save(rpe);}

    @Override
    public List<RPE> findByUser(User user) {return repository.findByUser(user);}

    @Override
    public Optional<RPE> findLastRPEsession(User user) {
        return repository.findTopByUserOrderByDateofTestDesc (user);
    }



}
