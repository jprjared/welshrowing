package com.team1.welshrowing.web;

import com.team1.welshrowing.domain.*;
import com.team1.welshrowing.repository.*;
import com.team1.welshrowing.service.UserCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
public class UserController {

    @Autowired
    private UserCreateService userCreateService;

    @Autowired
    private AthleteRepoJPA athleteRepo;

    @Autowired
    private ApplicantRepoJPA applicantRepo;

    @Autowired
    private InterviewRepoJPA interviewRepo;

    @Autowired
    private PersonalityInterviewRepoJPA personalityinterviewRepo;

    @Autowired
    private PhysicalTestRepoJPA physicalTestRepo;

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
                return "redirect:/register/details";
            }
        }

    /**
     * GETs the athlete details form.
     */
    @GetMapping("/register/details")
    public String RegisterDetails(Model model) {
        AthleteForm athleteForm = new AthleteForm();
        model.addAttribute("athlete", athleteForm);
        return "athlete-details-form";
    }

    /**
     * POSTs and saves form details in the Athlete's Repository
     * Redirects to athlete dashboard
     */
    @PostMapping("/register/process/details")
    public String ProcessAthleteForm(Athlete athlete) {
        athleteRepo.save(athlete);
        return "athlete-dashboard";
    }


    /**
     * GETs the interview form.
     */
    @GetMapping("/interview/{id}")
    public String InterviewForm(@PathVariable Long id, Model model) {
        InterviewForm interviewForm = new InterviewForm();
        interviewForm.setApplicantId(id);
        model.addAttribute("interview", interviewForm);
        return "interview-form";
    }

    /**
     * POSTs and saves form details in the interview repository
     * Redirects to athlete dashboard
     */
    @PostMapping("/interview/process")
    public String ProcessInterviewForm(Interview interview) {
        interviewRepo.save(interview);
        return "athlete-dashboard";
    }

    /**
     * GETs the personality interview form.
     */
    @GetMapping("/personality-interview/{id}")
    public String PersonalityInterviewForm(@PathVariable Long id, Model model) {
        PersonalityInterviewForm personalityInterviewForm = new PersonalityInterviewForm();
        personalityInterviewForm.setApplicantId(id);
        model.addAttribute("personalityinterview", personalityInterviewForm);
        return "personality-interview-form";
    }

    /**
     * POSTs and saves form details in the personality interview repository
     * Redirects to athlete dashboard
     */
    @PostMapping("/personality-interview/process")
    public String ProcessPersonalityInterviewForm(PersonalityInterview personalityInterview) {
        personalityinterviewRepo.save(personalityInterview);
        return "athlete-dashboard";
    }

    /**
     * GETs the physical test form.
     */
    @GetMapping("/physical-test/{id}")
    public String PhysicalTestingForm(@PathVariable Long id, Model model) {
        PhysicalTestForm physicalTestForm = new PhysicalTestForm();
        physicalTestForm.setApplicantId(id);
        model.addAttribute("physicaltest", physicalTestForm);
        return "physical-testing-form";
    }

    /**
     * POSTs and saves form details in the physical test repository
     * Redirects to athlete dashboard
     */
    @PostMapping("/physical-test/process")
    public String ProcessPhysicalTestingForm(PhysicalTest physicalTest) {
        physicalTestRepo.save(physicalTest);
        return "athlete-dashboard";
    }

    /**
     * GETs the application form.
     */
    @GetMapping("/application")
    public String ApplicationForm(Model model) {
        ApplicantForm applicantForm = new ApplicantForm();
        model.addAttribute("applicant", applicantForm);
        return "application-form";
    }

    /**
     * POSTs and saves form details in the Applicant's Repository
     * Redirects to athlete dashboard
     */
    @PostMapping("/application/process")
    public String ProcessApplicationForm(Applicant applicant) {
        applicantRepo.save(applicant);
        return "athlete-dashboard";
    }


}
