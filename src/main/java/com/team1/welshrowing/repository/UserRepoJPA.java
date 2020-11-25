package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("jpa")
public class UserRepoJPA implements UserRepo {

    private final UserJPA repository;

    @Autowired
    public UserRepoJPA(UserJPA repository) {
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

}
