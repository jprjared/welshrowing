package com.team1.welshrowing.web;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.RPE;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.InterviewRepoJPA;
import com.team1.welshrowing.repository.RPERepo;
import com.team1.welshrowing.repository.RPERepoJPA;
import com.team1.welshrowing.domain.XTraining;
import com.team1.welshrowing.security.UserDetailsImpl;
import com.team1.welshrowing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.*;
import java.util.Optional;
import java.util.Set;
import java.util.logging.ErrorManager;

@Controller
public class AthleteController {

    @Autowired
    MorningMonitoringCreateService morningMonitoringCreateService;

    @Autowired
    MorningMonitoringReadService morningMonitoringReadService;

    @Autowired
    private RPERepoJPA rpeRepo;

    @Autowired
    RPEReadService rpeReadService;
    XTrainingCreateService xtrainingCreateService;

    @Autowired
    XTrainingReadService xtrainingReadService;

    @Autowired
    UserReadService userReadService;

    @Autowired
    ApplicantReadService applicantReadService;

    /**
     * GETs the athlete dashboard.
     */
    @GetMapping("/athlete/dashboard")
    public String athleteDashboard(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> theUser = userReadService.findByUserName(((UserDetailsImpl)principal).getUsername());

            if (theUser.isPresent()) {

                Optional<Applicant> theApplicant = applicantReadService.findByUser(theUser.get());
                theApplicant.ifPresent(applicant -> model.addAttribute("applicant", applicant));

                // Has this user completed their daily morning monitoring?
                boolean hasCompletedMorningMonitoring = morningMonitoringReadService.hasCompletedMorningMonitoringToday(theUser.get());
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

    @GetMapping ("/athlete/RPE-form")
    public String rpeform(Model model) {
        model.addAttribute("RPE", new RPEForm());
        return "athlete/RPE-form";
    }

    /**
     * POSTs the athlete morning monitoring form.
     * @param rpeform - a morningMonitoring object
     * @return redirect to the dashboard
     */
    @PostMapping("/athlete/RPE-form")
    public String ProcessRPE(RPE rpeform) {
//        Ryans Code
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> theUser = userReadService.findByUserName(((UserDetailsImpl)principal).getUsername());
            theUser.ifPresent(rpeform::setUser);
        }
//

        rpeRepo.save(rpeform);
        return "redirect:/athlete/dashboard";
    }

    /**
     * GETs the athlete XTraining form.
     */
    @GetMapping("/athlete/x-training")
    public String XTraining(Model model) {
        model.addAttribute("XTraining", new XTraining());
        return "athlete/xtraining-form";
    }

    /**
     * POSTs the athlete XTraining form.
     * @param xTraining - a XTraining object
     * @return redirect to the dashboard
     */
    @PostMapping("/athlete/x-training")
    public String ProcessXTrainingForm(XTraining xTraining) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> theUser = userReadService.findByUserName(((UserDetailsImpl)principal).getUsername());
            theUser.ifPresent(xTraining::setUser);
        }

        xtrainingCreateService.addXTraining(xTraining);
        return "redirect:/athlete/dashboard";
    }

    /**
     * GETs the XTraining list
     */
    @GetMapping("/athlete/x-training/list")
    public String XTrainingList(Model model, XTraining xTraining) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> theUser = userReadService.findByUserName(((UserDetailsImpl)principal).getUsername());
            theUser.ifPresent(xTraining::setUser);
        }

        model.addAttribute("xtrainings", xtrainingReadService.findByUser(xTraining.getUser()));
        return "athlete/xtraining-list";
    }

}
