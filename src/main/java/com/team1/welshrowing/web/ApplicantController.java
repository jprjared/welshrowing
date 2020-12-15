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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ApplicantController {

    @Autowired
    public ApplicantRepoJPA applicantRepo;

    @Autowired
    UserReadService userReadService;

    @Autowired
    ApplicantReadService applicantReadService;

//    @GetMapping("/applicant/dashboard/{id}")
//    public String applicantDashboard(@PathVariable Long id, Model model) {
//
//
//
//        Optional<Applicant> applicant = applicantReadService.findById(1L);
//
//        String mockStatus = applicant.get().getApplication_situation();
//        model.addAttribute("applicant", applicant.get());
////        model.addAttribute("status", mockStatus);
//
//        return "applicant-dashboard";
//    }

    @GetMapping("/applicant/dashboard")
    public String athleteDashboard(Long id, Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> theUser = userReadService.findByUserName(((UserDetailsImpl)principal).getUsername());

            if (theUser.isPresent()) {


//                Optional<Applicant> theApplicant = applicantReadService.findById(id);
                Optional<Applicant> theApplicant = applicantReadService.findByUser(theUser.get());
                String mockStatus = theApplicant.get().getApplication_situation();
                theApplicant.ifPresent(applicant -> model.addAttribute("status", mockStatus));
                theApplicant.ifPresent(applicant -> model.addAttribute("applicant", applicant));


            }
        }
        return "applicant-dashboard";

    }



//    @GetMapping("/applicant/status/{id}/{application_situation}")
//    public String getApplicationStatus(@PathVariable Long id, @PathVariable, String application_situation, Model model) {
//
//        Optional<Applicant> applicant = applicantReadService.findById(id);
//        String status = applicant.get().getApplication_situation();
//
//        if (applicant.isPresent()){
//            model.addAttribute("applicant", applicant.get());
//
//            if(status == "Accepted" )
//            {
//                System.out.println("yeah!");
//                return "applicant-dashboard";
//
//            }
//
//            else if (status == "pending") {
//
//                System.out.println("pending");
//
//            }
//
//            else if (status == "Rejected") {
//
//                System.out.println("nahmate");
//
//            }
//
//            else {
//                System.out.println("error");
//
//            }
//
//
//        }
//
//        return "applicant-dashboard";
//    }

//    @GetMapping("applicant/status/{id}/{status}")
//    public String getAS(@PathVariable Long id, @PathVariable String status, Model model) {
//
//
//
//        return null;
//    }

//    @GetMapping(path = "/coach/applicant/feedback/{id}")
//    public String getApplicationStatus(@PathVariable Long id, Model model) {
//
//        Optional<Applicant> applicant = applicantReadService.findById(id);
//
//        if (applicant.isPresent()) {
//            Feedback feedbackForm = new Feedback();
//            model.addAttribute("applicant", applicant.get());
//            model.addAttribute("user", applicant.get().getUser());
//            model.addAttribute("feedback",feedbackForm);
//
//            return "feedback-form";
//        } else {
//            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
//        }
//    }

}
