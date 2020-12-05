package com.team1.welshrowing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicantController {

    @GetMapping("/applicant/dashboard")
    public String applicantDashboard(Model model) {

//        model.addAttribute("")
        return "applicant-dashboard";
    }

//    @PostMapping("/application/dashboard/status")
//    public String getStatus() {
//
//
//    }
}
