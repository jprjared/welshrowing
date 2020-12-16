package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.RPE;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;

import java.util.List;
import java.util.Optional;

public interface RPERepo {
    void saveRPE(RPE rpe);
    List<RPE> findByUser(User user);
    Optional<RPE> findLastRPEsession(User user);
}
