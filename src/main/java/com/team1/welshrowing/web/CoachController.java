package com.team1.welshrowing.web;


import com.team1.welshrowing.repository.ApplicantRepoJPA;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoachController {

    @Autowired
    public ApplicantRepoJPA applicantRepo ;


    @GetMapping("/allApplicants")
    public String getApplicant(Model model){

        model.addAttribute("applicants", applicantRepo.findAll());
        System.out.println(applicantRepo.findAll());
        return "applicantList";
    }





}
