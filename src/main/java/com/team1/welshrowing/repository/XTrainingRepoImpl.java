package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class XTrainingRepoImpl implements XTrainingRepo {

    private final XTrainingRepoJPA repository;

    public XTrainingRepoImpl(XTrainingRepoJPA repository) {
        this.repository = repository;
    }

    @Override
    public void saveXTraining(XTraining xTraining) {
        repository.save(xTraining);
    }

    @Override
    public List<XTraining> findByUser(User user) {
        return repository.findByUserOrderByDateOfTrainingDesc(user);
    }

    @Override
    public Optional<XTraining> findLatestXTraining(User user) {
        return repository.findTopByUserOrderByDateOfTrainingDesc(user);
    }

}
