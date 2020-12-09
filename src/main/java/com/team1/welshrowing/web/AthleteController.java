package com.team1.welshrowing.web;

import com.team1.welshrowing.domain.MorningMonitoring;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AthleteController {

    /**
     * GETs the athlete details form.
     */
    @GetMapping("/athlete/morning-monitoring")
    public String morningMonitoring(Model model) {
        model.addAttribute("morningMonitoring", new MorningMonitoring());
        return "athlete/morning-monitoring";
    }

}
