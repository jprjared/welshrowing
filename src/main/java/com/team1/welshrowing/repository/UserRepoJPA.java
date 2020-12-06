package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepoJPA extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    @Query("SELECT COUNT(userId) FROM #{#entityName} WHERE email= :email OR userName= :userName")
    Integer countUserIdWithEmailOrUsername(@Param("email") String aEmail, @Param("userName") String aUsername);
}
