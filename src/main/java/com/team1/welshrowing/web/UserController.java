package com.team1.welshrowing.web;

import com.team1.welshrowing.domain.*;
import com.team1.welshrowing.repository.AthleteRepoJPA;
import com.team1.welshrowing.repository.InterviewRepoJPA;
import com.team1.welshrowing.repository.PersonalityInterviewRepoJPA;
import com.team1.welshrowing.repository.PhysicalTestRepoJPA;
import com.team1.welshrowing.security.UserDetailsImpl;
import com.team1.welshrowing.service.ApplicantCreateService;
import com.team1.welshrowing.service.ApplicantReadService;
import com.team1.welshrowing.service.UserCreateService;
import com.team1.welshrowing.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class UserController {

    @Autowired
    private UserCreateService userCreateService;

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private ApplicantCreateService applicantCreateService;

    @Autowired
    private ApplicantReadService applicantReadService;

    @Autowired
    private AthleteRepoJPA athleteRepo;

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
    public String ProcessRegisterForm(HttpServletRequest request, User user, BindingResult bindings){

        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            return "user-signup-form";
        } else {

            String pass = user.getPassword();

            user.setRoles("ATHLETE");
            userCreateService.addUser(user);

            // Adapted from code examples at https://www.baeldung.com/spring-security-auto-login-user-after-registration [Accessed: 1 December 2020]
            try {
                SecurityContextHolder.getContext().setAuthentication(null);
                request.login(user.getUserName(), pass);
            } catch (ServletException e) {
                System.out.println(e);
                throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Login was not possible");
            }

            return "redirect:/register/application";
        }
    }

    /**
     * GETs the athlete details form.
     */
    @GetMapping("/register/application")
    public String RegisterApplication(Model model) {
        model.addAttribute("applicant", new Applicant());
        return "application-form";
    }

    /**
     * POSTs and saves form details in the Applicant's Repository
     * Redirects to athlete dashboard
     */
    @PostMapping("/application/process")
    public String ProcessApplicationForm(Applicant applicant) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> theUser = userReadService.findByUserName(((UserDetailsImpl)principal).getUsername());
            theUser.ifPresent(applicant::setUser);
        }

        applicantCreateService.addApplicant(applicant);
        return "redirect:/athlete/dashboard";
    }

    /**
     * GETs the accepted application list
     */
    @GetMapping("/application/status")
    public String AcceptedApplicants(Model model) {
        model.addAttribute("applicants", applicantReadService.findByStatus("Accepted"));
        return "applicant-accepted-list";
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
        return "redirect:/personality-interview/" + interview.getApplicantId();
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
        return "coach/dashboard";
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
        return "coach/dashboard";
    }
}
