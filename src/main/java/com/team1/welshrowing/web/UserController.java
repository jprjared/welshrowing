package com.team1.welshrowing.web;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.UserRepoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepoJPA userRepo;

    @GetMapping("/register")
    public String RegisterForm(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("user", userForm);
        return "user-signup-form";
//        return "redirect:register/details";

    }

    @PostMapping("/register/details")
    public String ProcessRegisterForm(User user){
        userRepo.save(user);
        return "athlete-details-form";
    }

}
