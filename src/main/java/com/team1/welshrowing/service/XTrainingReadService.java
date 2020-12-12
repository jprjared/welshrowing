package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;
import com.team1.welshrowing.repository.XTrainingRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class XTrainingReadService {

    private final XTrainingRepo repository;

    public XTrainingReadService(XTrainingRepo repository) {
        this.repository = repository;
    }

    public List<XTraining> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Optional<XTraining> getLastXTraining(User user) {
        return repository.findLatestXTraining(user);
    }
}
