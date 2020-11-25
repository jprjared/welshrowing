package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService {

    private final UserRepo repository;

    @Autowired
    public UserCreateService(UserRepo repository) {
        this.repository = repository;
    }

    public void addUser(User user) {
        repository.saveUser(user);
    }

}
