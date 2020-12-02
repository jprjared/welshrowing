package com.team1.welshrowing.web;


import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.repository.ApplicantRepoJPA;;
import com.team1.welshrowing.service.ApplicantReadService;
import com.team1.welshrowing.service.ApplicantUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CoachController {

    @Autowired
    public ApplicantRepoJPA applicantRepo ;

    @Autowired
    private ApplicantUpdateService applicantUpdateService;

    @Autowired
    private ApplicantReadService applicantReadService;

    /**
     * GETs the athlete dashboard form.
     */
//    @GetMapping("/allApplicants")
//    public String allApplicants() {
//        return "applicantList";
//    }


    @GetMapping("/allApplicants")
    public String getApplicant(Model model){

        model.addAttribute("applicants", applicantRepo.findAll());
        System.out.println(applicantRepo.findAll());
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

}
