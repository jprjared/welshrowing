package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
public class UserRepoImpl implements UserRepo {

    private final UserRepoJPA repository;

    @Autowired
    public UserRepoImpl(UserRepoJPA repository) {
        this.repository = repository;
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

}
