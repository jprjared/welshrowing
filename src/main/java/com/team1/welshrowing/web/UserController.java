package com.team1.welshrowing.web;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.Athlete;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.AthleteRepoJPA;
import com.team1.welshrowing.service.ApplicantCreateService;
import com.team1.welshrowing.service.ApplicantReadService;
import com.team1.welshrowing.service.UserCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserCreateService userCreateService;

    @Autowired
    private ApplicantCreateService applicantCreateService;

    @Autowired
    private ApplicantReadService applicantReadService;

    @Autowired
    private AthleteRepoJPA athleteRepo;

    /**
     * GETs the user sign-up form.
     */
    @GetMapping("/register")
    public String RegisterForm(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("user", userForm);
        return "user-signup-form";
    }

    /**
     * GETs the user log in form.
     * @return the login page template.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * GETs the athlete dashboard form.
     */
    @GetMapping("/athlete/dashboard")
    public String athleteDashboard() {
        return "athlete-dashboard";
    }

    /**
     * POSTs and saves form details in the User's Repository
     * Redirects to athlete details form
     * Catches any errors and returns to the previous form
     */
    @PostMapping("/register/process")
    public String ProcessRegisterForm(User user, BindingResult bindings){

        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            return "user-signup-form";
        } else {
                user.setRoles("ATHLETE");
                userCreateService.addUser(user);
                return "redirect:/register/application";
            }
        }


    /**
     * POSTs and saves form details in the Athlete's Repository
     * Redirects to athlete dashboard
     */
    @PostMapping("/register/process/details")
    public String ProcessAthleteForm(Athlete athlete) {
        athleteRepo.save(athlete);
        return "redirect:/athlete/dashboard";
    }

    /**
     * GETs the application form.
     */
//    @GetMapping("/application")
//    public String ApplicationForm(Model model) {

    @GetMapping("/register/application")
    public String RegisterApplication(Model model) {

        Applicant applicant = new Applicant();
        model.addAttribute("applicants", applicant);
        return "application-form";
    }

    /**
     * POSTs and saves form details in the Applicant's Repository
     * Redirects to athlete dashboard
     */
    @PostMapping("/application/process")
    public String ProcessApplicationForm(Applicant applicant) {
        applicantCreateService.addApplicant(applicant);
        return "redirect:/coach/dashboard";
    }

    /**
     * GETs the accepted application list
     */
    @GetMapping("/application/status")
    public String AcceptedApplicants(Model model) {
        model.addAttribute("applicants", applicantReadService.findByStatus("Accepted"));
        return "applicant-accepted-list";
    }

//    /**
//     * GETs the athlete details form.
//     */
//<<<<<<< HEAD
//    @GetMapping("/register/details")
//    public String RegisterDetails(Model model) {
//        Applicant applicant = new Applicant();
//        model.addAttribute("applicants", applicant);
//        return "application-form";
//    }

}
