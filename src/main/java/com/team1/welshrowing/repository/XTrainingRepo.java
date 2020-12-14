package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;

import java.util.List;
import java.util.Optional;

public interface XTrainingRepo {
    void saveXTraining(XTraining xTraining);

    List<XTraining> findByUser(User user);

    Optional<XTraining> findLatestXTraining(User user);
}
