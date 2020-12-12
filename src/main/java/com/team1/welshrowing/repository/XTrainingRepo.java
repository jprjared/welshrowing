package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;

import java.util.List;

public interface XTrainingRepo {
    void saveXTraining(XTraining xTraining);

    List<XTraining> findByUser(User user);
}
