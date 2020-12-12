package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;
import com.team1.welshrowing.repository.XTrainingRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XTrainingReadService {

    private final XTrainingRepo repository;

    public XTrainingReadService(XTrainingRepo repository) {
        this.repository = repository;
    }

    public List<XTraining> findByUser(User user) {
        return repository.findByUser(user);
    }

}
