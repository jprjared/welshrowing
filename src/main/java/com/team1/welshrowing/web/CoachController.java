package com.team1.welshrowing.web;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.service.ApplicantReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import java.util.MissingResourceException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class CoachController {

    @Autowired
    private ApplicantReadService applicantReadService;

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
            return "coach/view-details";
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Applicant not found");
        }

    }


}
