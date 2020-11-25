package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService {

    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserCreateService(UserRepo repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.saveUser(user);
    }

}
