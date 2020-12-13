package com.team1.welshrowing.web;

import com.team1.welshrowing.repository.ApplicantRepoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicantController {

    @Autowired
    public ApplicantRepoJPA applicantRepo;


    @GetMapping("/applicant/dashboard")
    public String applicantDashboard(Model model) {

        model.addAttribute("applicants", applicantRepo.findAll());
        return "applicant-dashboard";
    }

//    @PostMapping("/application/dashboard/status")
//    public String getStatus() {
//
//
//    }
}
