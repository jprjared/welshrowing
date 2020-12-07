package com.team1.welshrowing.web;

import com.cemiltokatli.passwordgenerate.Password;
import com.cemiltokatli.passwordgenerate.PasswordType;
import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.ApplicationForm;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.ApplicantFormRepoJPA;
import com.team1.welshrowing.repository.ApplicantRepoJPA;
import com.team1.welshrowing.service.ApplicantReadService;
import com.team1.welshrowing.service.ApplicantUpdateService;
import com.team1.welshrowing.service.UserCreateService;
import com.team1.welshrowing.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class CoachController {

    @Autowired
    private UserCreateService userCreateService;

    @Autowired
    private ApplicantReadService applicantReadService;

    @Autowired
    private UserReadService userReadService;
    
    @Autowired
    public ApplicantRepoJPA applicantRepo;

    @Autowired
    private ApplicantUpdateService applicantUpdateService;

    @Autowired
    private ApplicantFormRepoJPA applicantFormRepo;

    /**
     * GETs the coach dashboard
     */
    @GetMapping("/coach/dashboard")
    public String coachDashboard() {
        return "coach/dashboard";
    }

    /**
     * GETs an applicant by their ID
     * @param id - the ID of the applicant
     * @param model - the Model object
     * @return - the view-details template
     */
    @GetMapping(path = "/coach/applicant/{id}")
    public String getApplicantDetails(@PathVariable Long id, Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);
        Optional<User> user = userReadService.findById(id);

        if (applicant.isPresent() && user.isPresent()) {
            model.addAttribute("applicant", applicant.get());
            model.addAttribute("user", user.get());
            return "coach/view-details";
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }

    }

    /**
     * GETs the add coach form.
     */
    @GetMapping("/coach/add-coach")
    public String getAddCoach(Model model) {
        model.addAttribute("user", new User());
        return "coach/add-coach";
    }

    @PostMapping("/coach/add-coach")
    public String postAddCoach(Model model, User user) {

        // Generate a random password
        Password pass = Password.createPassword();
        pass.setType(PasswordType.ALPHANUMERIC);
        pass.setMinLength(8);
        pass.setMaxLength(10);
        String password = pass.generate();

        // Add to the model for display purposes
        model.addAttribute("password", password);

        user.setPassword(password);
        user.setRoles("COACH");

        userCreateService.addUser(user);
        return "coach/add-coach";
    }
    
    @GetMapping("/allApplicants")
    public String getApplicant(Model model){

        model.addAttribute("applicants", applicantRepo.findAll());
        return "applicantList";
    }

    @PostMapping("/allApplicants/accept/{id}")
    public String AcceptAnApplicant(@PathVariable Long id) {
        Optional<Applicant> applicant = applicantReadService.findById(id);
        applicantUpdateService.updateApplicantStatus(applicant.get(), "Accepted");


        return "redirect:/allApplicants";
    }

    @PostMapping("/allApplicants/reject/{id}")
    public String RejectAnApplicant(@PathVariable Long id) {
        Optional<Applicant> applicant = applicantReadService.findById(id);
        applicantUpdateService.updateApplicantStatus(applicant.get(), "Rejected");
        return "redirect:/allApplicants";
    }

//    @GetMapping("/allApplicants/applicationForm")
//    public String SubmitApplicationForm(Model model) {
//        model.addAttribute("applicationform", applicantFormRepo.findAll());
//
//        Optional<Applicant> applicant = applicantReadService.findById(id);
//        applicantUpdateService.updateApplicantStatus(applicant.get(), "Accepted");
//        return "redirect:/allApplicants";
//    }

}