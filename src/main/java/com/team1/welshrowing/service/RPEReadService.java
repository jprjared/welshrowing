package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.RPE;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;
import com.team1.welshrowing.repository.MorningMonitoringRepo;
import com.team1.welshrowing.repository.RPERepo;
import com.team1.welshrowing.web.RPEForm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

;

@Service
public class RPEReadService {

    private final RPERepo repository;

    @Autowired
    public RPEReadService(RPERepo repository) {
        this.repository = repository;
    }

    public List<RPE> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Optional<RPE> getLastRPE(User user) {
        return repository.findLastRPEsession(user);
    }


}
