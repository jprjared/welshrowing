package com.team1.welshrowing.service;

import com.team1.welshrowing.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {

    private final UserRepo repo;

    @Autowired
    public UserUpdateService(UserRepo repo) {this.repo = repo;}

    public void roleUpdate(String newRole, Long userID) {
        repo.UserRoleUpdate(newRole, userID);
    }
}
