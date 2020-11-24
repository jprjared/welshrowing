package com.team1.welshrowing.web;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.Athlete;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.ApplicantRepoJPA;
import com.team1.welshrowing.repository.AthleteRepoJPA;
import com.team1.welshrowing.repository.UserRepoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepoJPA userRepo;

    @Autowired
    private AthleteRepoJPA athleteRepo;

    @Autowired
    private ApplicantRepoJPA applicantRepo;


    @GetMapping("/register")
    public String RegisterForm(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("user", userForm);
        return "user-signup-form";
    }

    @PostMapping("/register/process")
    public String ProcessRegisterForm(User user){
        userRepo.save(user);
        return "redirect:/register/details";
    }

    @GetMapping("/register/details")
    public String RegisterDetails(Model model) {
        AthleteForm athleteForm = new AthleteForm();
        model.addAttribute("athlete", athleteForm);
        return "athlete-details-form";
//        return "redirect:register/details";
    }

    @PostMapping("/register/process/details")
    public String ProcessAthleteForm(Athlete athlete) {
        athleteRepo.save(athlete);
        return "athlete-dashboard";
    }

    @GetMapping("/application")
    public String ApplicationForm(Model model) {
        ApplicantForm applicantForm = new ApplicantForm();
        model.addAttribute("applicant", applicantForm);
        return "application-form";
    }

    @PostMapping("/application/process")
    public String ProcessApplicationForm(Applicant applicant) {
        applicantRepo.save(applicant);
        return "athlete-dashboard";
    }





}
