package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.MorningMonitoringRepo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

;

@Service
public class MorningMonitoringReadService {

    private final MorningMonitoringRepo repository;

    @Autowired
    public MorningMonitoringReadService(MorningMonitoringRepo repository) {
        this.repository = repository;
    }

    public boolean hasCompletedMorningMonitoringToday(User user) {

        // Get today's date
        Date today = new Date();

        // Get all morning monitoring forms for this user
        List<MorningMonitoring> userForms = repository.findByUser(user);

        Predicate<MorningMonitoring> aPredicate = form -> DateUtils.isSameDay(form.getDateTime(), today);
        // Filter by forms that were completed today
        List<MorningMonitoring> todaysForms = userForms.stream()
                .filter(aPredicate)
                .collect(Collectors.toList());

        // True if there were forms completed today
        return todaysForms.size() > 0;

    }


}
