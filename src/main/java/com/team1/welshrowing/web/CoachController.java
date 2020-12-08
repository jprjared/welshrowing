package com.team1.welshrowing.web;

import com.cemiltokatli.passwordgenerate.Password;
import com.cemiltokatli.passwordgenerate.PasswordType;
import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.Feedback;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.ApplicantRepoJPA;
import com.team1.welshrowing.repository.FeedbackRepoJPA;
import com.team1.welshrowing.service.ApplicantEmailService;
import com.team1.welshrowing.service.ApplicantReadService;
import com.team1.welshrowing.service.ApplicantUpdateService;
import com.team1.welshrowing.service.FeedbackCreateService;
import com.team1.welshrowing.service.FeedbackReadService;
import com.team1.welshrowing.service.UserCreateService;
import com.team1.welshrowing.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private ApplicantEmailService applicantEmailService;

    @Autowired
    private FeedbackReadService feedbackReadService;

    @Autowired
    private FeedbackCreateService feedbackCreateService;

    @Autowired
    private UserReadService userReadService;
    
    @Autowired
    public ApplicantRepoJPA applicantRepo;

    @Autowired
    public FeedbackRepoJPA feedbackRepo;

    @Autowired
    private ApplicantUpdateService applicantUpdateService;

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

        if (applicant.isPresent()) {
            model.addAttribute("applicant", applicant.get());
            model.addAttribute("user", applicant.get().getUser());
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
        System.out.println(applicantRepo.findAll());
        return "applicantList";
    }

    @PostMapping("coach/applicant/accept/{id}")
    public String AcceptApplication(@PathVariable Long id, Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);

        if (applicant.isPresent()) {

            model.addAttribute("applicant", applicant.get());
            model.addAttribute("user", applicant.get().getUser().getEmail());

            applicantUpdateService.updateApplicantStatus(applicant.get(), "Accepted");
            applicantEmailService.sendApplicantEmailAcceptReject(applicant.get());

            return "redirect:/allApplicants";
        } else {

            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }
    }

    @PostMapping("/coach/applicant/reject/{id}")
    public String RejectApplication(@PathVariable Long id,Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);

        if (applicant.isPresent()) {

            model.addAttribute("applicant", applicant.get());
            model.addAttribute("user", applicant.get().getUser().getEmail());

            applicantUpdateService.updateApplicantStatus(applicant.get(), "Rejected");
            applicantEmailService.sendApplicantEmailAcceptReject(applicant.get());

            return "redirect:/allApplicants";
        } else {

            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }
    }

    @PostMapping("coach/applicant/pass/{id}")
    public String PassAnApplicant(@PathVariable Long id, Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);

        if (applicant.isPresent()) {

            model.addAttribute("applicant", applicant.get());
            model.addAttribute("user", applicant.get().getUser().getEmail());

            applicantUpdateService.updateApplicantStatus(applicant.get(), "Passed");
            applicantEmailService.sendApplicantEmailPassFail(applicant.get());

            return "redirect:/allApplicants";
        } else {

            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }
    }

    @PostMapping("coach/applicant/fail/{id}")
    public String FailAnApplicant(@PathVariable Long id, Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);

        if (applicant.isPresent()) {

            model.addAttribute("applicant", applicant.get());
            model.addAttribute("user", applicant.get().getUser().getEmail());

            applicantUpdateService.updateApplicantStatus(applicant.get(), "Failed");
            applicantEmailService.sendApplicantEmailPassFail(applicant.get());

            return "redirect:/coach/applicant/feedback/{id}";
        } else {

            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }
    }



    @GetMapping(path = "/coach/applicant/feedback/{id}")
    public String getApplicantFeedback(@PathVariable Long id, Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);

        if (applicant.isPresent()) {
            Feedback feedbackForm = new Feedback();
            model.addAttribute("applicant", applicant.get());
            model.addAttribute("user", applicant.get().getUser());
            model.addAttribute("feedback",feedbackForm);

            return "feedback-form";
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }
    }

    @PostMapping("coach/applicant/feedback/send/{id}")
    public String sendApplicantFeedback(Feedback feedback) {
        System.out.println(feedback);
        Optional<Applicant> applicant = applicantReadService.findById(feedback.getApplicantId());
        feedbackCreateService.addFeedback(feedback);

        if (applicant.isPresent()) {

            applicantEmailService.sendApplicantFeedback(applicant.get(),feedback.getMessage(),feedback.getFile());

            return "redirect:/allApplicants";
        } else {

            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }
    }

}