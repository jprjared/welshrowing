package com.team1.welshrowing.web;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.ApplicantRepoJPA;
import com.team1.welshrowing.security.UserDetailsImpl;
import com.team1.welshrowing.service.ApplicantReadService;
import com.team1.welshrowing.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class ApplicantController {

    @Autowired
    public ApplicantRepoJPA applicantRepo;

    @Autowired
    UserReadService userReadService;

    @Autowired
    ApplicantReadService applicantReadService;

    @GetMapping("/applicant/dashboard")
    public String athleteDashboard(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> theUser = userReadService.findByUserName(((UserDetailsImpl)principal).getUsername());

            if (theUser.isPresent()) {

                Optional<Applicant> theApplicant = applicantReadService.findByUser(theUser.get());
                String status = theApplicant.get().getApplication_situation();
                theApplicant.ifPresent(applicant -> model.addAttribute("status", status));
                theApplicant.ifPresent(applicant -> model.addAttribute("applicant", applicant));

                if (theApplicant.isPresent()) {
                    if (theApplicant.get().getApplication_situation().equals("Passed")) {
                        return "redirect:/athlete/dashboard";
                    }
                }

            }
        }
        return "applicant-dashboard";
    }
}
