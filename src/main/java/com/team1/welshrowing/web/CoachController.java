package com.team1.welshrowing.web;

import com.cemiltokatli.passwordgenerate.Password;
import com.cemiltokatli.passwordgenerate.PasswordType;
import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.Feedback;
import com.team1.welshrowing.domain.Interview;
import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.PersonalityInterview;
import com.team1.welshrowing.domain.PhysicalTest;
import com.team1.welshrowing.domain.RPE;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;
import com.team1.welshrowing.repository.ApplicantRepoJPA;
import com.team1.welshrowing.repository.FeedbackRepoJPA;
import com.team1.welshrowing.security.UserDetailsImpl;
import com.team1.welshrowing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import javax.servlet.http.HttpServletRequest;
import java.util.MissingResourceException;


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

    @Autowired
    private UserUpdateService userUpdateService;

    @Autowired
    private InterviewReadService interviewReadService;

    @Autowired
    private PersonalityInterviewReadService personalityInterviewReadService;

    @Autowired
    private PhysicalTestReadService physicalTestReadService;

    @Autowired
    private XTrainingReadService xTrainingReadService;

    @Autowired
    private RPEReadService rpeReadService;

    @Autowired
    private MorningMonitoringReadService morningMonitoringReadService;

    /**
     * GETs the coach dashboard
     */
    @GetMapping("/coach/dashboard")
    public String coachDashboard() {
        return "coach/dashboard";
    }

    /**
     * GETs an applicant by their ID
     *
     * @param id    - the ID of the applicant
     * @param model - the Model object
     * @return - the view-details template
     */
    @GetMapping(path = "/coach/applicant/{id}")
    public String getApplicantDetails(@PathVariable Long id, Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);

        if (applicant.isPresent()) {
            model.addAttribute("applicant", applicant.get());
            model.addAttribute("user", applicant.get().getUser());

            // Find interview and physical testing forms
            Optional<Interview> interview = interviewReadService.findByApplicantId(applicant.get().getApplicantId());
            interview.ifPresent(value -> model.addAttribute("interview", value));

            Optional<PersonalityInterview> personalityInterview = personalityInterviewReadService.findByApplicantId(applicant.get().getApplicantId());
            personalityInterview.ifPresent(value -> model.addAttribute("personalityInterview", value));

            Optional<PhysicalTest> physicalTest = physicalTestReadService.findByApplicantId(applicant.get().getApplicantId());
            physicalTest.ifPresent(value -> model.addAttribute("physicalTest", value));

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

    @GetMapping("/coach/allApplicants")
    public String getApplicant(Model model) {

        model.addAttribute("applicants", applicantRepo.findAll());
        return "applicant-list";

    }

    @PostMapping("coach/applicant/accept/{id}")
    public String AcceptApplication(@PathVariable Long id, Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);

        if (applicant.isPresent()) {

            model.addAttribute("applicant", applicant.get());
            model.addAttribute("user", applicant.get().getUser().getEmail());

            applicantUpdateService.updateByStatus("Accepted",applicant.get().getApplicantId());
            applicantEmailService.sendApplicantEmailAcceptReject("Accepted", applicant.get());

            return "redirect:/coach/allApplicants";
        } else {

            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }
    }

    @PostMapping("/coach/applicant/reject/{id}")
    public String RejectAnApplicant(@PathVariable Long id, Model model) {


        Optional<Applicant> applicant = applicantReadService.findById(id);

        if (applicant.isPresent()) {

            model.addAttribute("applicant", applicant.get());
            model.addAttribute("user", applicant.get().getUser().getEmail());

            applicantUpdateService.updateByStatus("Rejected",applicant.get().getApplicantId());
            applicantEmailService.sendApplicantEmailAcceptReject("Rejected",applicant.get());

            return "redirect:/coach/allApplicants";
        } else {

            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }
    }

    @PostMapping("coach/applicant/pass/{id}")
    public String PassAnApplicant(@PathVariable Long id, Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);
        //line added to fix the authorisation problems
        Optional<User> user = userReadService.findById(id);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> theUser = userReadService.findByUserName(((UserDetailsImpl) principal).getUsername());

            if (theUser.isPresent()) {
                //        if (applicant.isPresent()) {

                model.addAttribute("applicant", applicant.get());
                model.addAttribute("user", applicant.get().getUser().getEmail());

                applicantUpdateService.updateByStatus("Passed", applicant.get().getApplicantId());
                applicantEmailService.sendApplicantEmailPassFail("Passed", applicant.get());

                //line added to fix authorisation problems
                userUpdateService.roleUpdate("ATHLETE", user.get().getUserId());

            }
        }
        else {

            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }
        return "redirect:/coach/allApplicants";
    }

    @PostMapping("coach/applicant/fail/{id}")
    public String FailAnApplicant(@PathVariable Long id, Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);

        if (applicant.isPresent()) {

            model.addAttribute("applicant", applicant.get());
            model.addAttribute("user", applicant.get().getUser().getEmail());

            applicantUpdateService.updateByStatus("Failed",applicant.get().getApplicantId());
            applicantEmailService.sendApplicantEmailPassFail("Failed",applicant.get());

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

            return "redirect:/coach/allApplicants";
        } else {

            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }
    }
    /**
     * GETs an applicant by their ID
     * @param id - the ID of the applicant
     * @param model - the Model object
     * @return - the view-details-athlete template
     */
    @GetMapping(path = "/coach/athlete/{id}")
    public String getAthleteDetails(@PathVariable Long id, Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);

        if (applicant.isPresent()) {
            model.addAttribute("applicant", applicant.get());
            model.addAttribute("user", applicant.get().getUser());

            // Find X training
            Optional<XTraining> xtraining = xTrainingReadService.getLastXTraining(applicant.get().getUser());
            xtraining.ifPresent(value -> model.addAttribute("xtraining", value));
          Optional<RPE> rpe = rpeReadService.getLastRPE(applicant.get().getUser());
          rpe.ifPresent(value -> model.addAttribute("rpes", value));

            // Find morning monitoring
            Optional<MorningMonitoring> morningMonitoring = morningMonitoringReadService.findLatestByUser(applicant.get().getUser());
            morningMonitoring.ifPresent(value -> model.addAttribute("morningMonitoring", value));

            return "coach/view-details-athlete";
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Athlete not found");
        }
    }

    /**
     * GETs the morning monitoring form for a specific applicant
     * @param id - the id of the applicant
     * @param model - a Model object
     * @return - the morning monitoring template
     */
    @GetMapping(path = "/coach/morning-monitoring/{id}")
    public String getMorningMonitoring(@PathVariable Long id, Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);

        if (applicant.isPresent()) {
            model.addAttribute("applicant", applicant.get());
            return "coach/morning-monitoring";
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Athlete not found");
        }
    }

    /**
     * GETs the XTraining list
     */
    @GetMapping("/coach/x-training/{id}")
    public String XTrainingList(@PathVariable Long id, Model model, XTraining xTraining) {

        Optional<User> user = userReadService.findById(id);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> theUser = userReadService.findByUserName(((UserDetailsImpl)principal).getUsername());
            theUser.ifPresent(xTraining::setUser);
        }

        model.addAttribute("xtrainings", xTrainingReadService.findByUser(user.get()));
        return "athlete/xtraining-list";
    }

    @GetMapping("/coach/RPE-form/{id}")
    public String RPEList(@PathVariable Long id, Model model, RPE rpe) {

        Optional<User> user = userReadService.findById(id);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> theUser = userReadService.findByUserName(((UserDetailsImpl)principal).getUsername());
            theUser.ifPresent(rpe::setUser);
        }
        model.addAttribute("rpes", rpeReadService.findByUser(user.get()));
        return "athlete/RPE-form-list";
    }


    @PostMapping("/coach/applicant/save-comments/{id}")
    public String SaveComment(@PathVariable Long id, String comments, Model model) {

        Optional<Applicant> applicant = applicantReadService.findById(id);

        if (applicant.isPresent()) {

            model.addAttribute("applicant", applicant.get());
            applicantUpdateService.commentUpdate(comments, applicant.get().getApplicantId());

            return "redirect:/coach/allApplicants";
        } else {

            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }

    }
}