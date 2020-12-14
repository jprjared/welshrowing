package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.XTraining;
import com.team1.welshrowing.repository.XTrainingRepo;
import org.springframework.stereotype.Service;

@Service
public class XTrainingCreateService {

    private final XTrainingRepo repository;

    public XTrainingCreateService(XTrainingRepo repository) {
        this.repository = repository;
    }

    public void addXTraining(XTraining xTraining) {
        repository.saveXTraining(xTraining);
    }
}
