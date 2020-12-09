package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.MorningMonitoringRepo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

        // Get all user forms for this user
        List<MorningMonitoring> userForms = repository.findByUser(user);

        for (MorningMonitoring form : userForms) {
            // Was this form completed today?
            boolean isOnSameDay = DateUtils.isSameDay(form.getDateTime(), today);
            if (isOnSameDay) {
                return true;
            }
        }

        return false;

    }


}
