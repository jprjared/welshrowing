package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
        return repository.findByUserNameIgnoreCase(userName);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

}
