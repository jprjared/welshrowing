package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepoJPA extends JpaRepository<User, Long> {
    Optional<User> findFirstByUserNameIgnoreCase(String userName);

    @Transactional
    @Modifying
    @Query("UPDATE #{#entityName} SET roles=?1 WHERE userId=?2")
    void updateRole(String newRole, Long userID);
}
