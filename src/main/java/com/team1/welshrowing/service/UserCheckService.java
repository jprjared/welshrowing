package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCheckService {

    private final UserRepo repository;

    @Autowired
    public UserCheckService(UserRepo repository) {
        this.repository = repository;
    }

    public Boolean isValidEmailAndUsername(User user){
       return repository.isValidEmailAndUsername(user,user.getEmail(),user.getUserName());
    }
}
