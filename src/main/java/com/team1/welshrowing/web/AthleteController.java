package com.team1.welshrowing.web;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.security.UserDetailsImpl;
import com.team1.welshrowing.service.MorningMonitoringCreateService;
import com.team1.welshrowing.service.MorningMonitoringReadService;
import com.team1.welshrowing.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AthleteController {

    @Autowired
    MorningMonitoringCreateService morningMonitoringCreateService;

    @Autowired
    MorningMonitoringReadService morningMonitoringReadService;

    @Autowired
    UserReadService userReadService;

    /**
     * GETs the athlete dashboard.
     */
    @GetMapping("/athlete/dashboard")
    public String athleteDashboard(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> theUser = userReadService.findByUserName(((UserDetailsImpl)principal).getUsername());

            if (theUser.isPresent()) {

                // Has this user completed their daily morning monitoring?
                boolean hasCompletedMorningMonitoring = morningMonitoringReadService.hasCompletedMorningMonitoringToday(theUser.get());

                model.addAttribute("user", theUser.get());
                model.addAttribute("hasCompletedMorningMonitoring", hasCompletedMorningMonitoring);
            }
        }
        return "athlete-dashboard";

    }

    /**
     * GETs the athlete morning monitoring form.
     */
    @GetMapping("/athlete/morning-monitoring")
    public String morningMonitoring(Model model) {
        model.addAttribute("morningMonitoring", new MorningMonitoring());
        return "athlete/morning-monitoring";
    }

    /**
     * POSTs the athlete morning monitoring form.
     * @param form - a morningMonitoring object
     * @return redirect to the dashboard
     */
    @PostMapping("/athlete/morning-monitoring")
    public String ProcessPhysicalTestingForm(MorningMonitoring form) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> theUser = userReadService.findByUserName(((UserDetailsImpl)principal).getUsername());
            theUser.ifPresent(form::setUser);
        }

        morningMonitoringCreateService.addMorningMonitoring(form);
        return "redirect:/athlete/dashboard";
    }

}
