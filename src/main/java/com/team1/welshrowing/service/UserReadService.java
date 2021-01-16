package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserReadService {

    private final UserRepo repository;

    @Autowired
    public UserReadService(UserRepo repository) {
        this.repository = repository;
    }

    public Optional<User> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

}
