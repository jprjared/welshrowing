package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ApplicantRepoJPA extends JpaRepository<Applicant, Long> {

    /**
     * JPQL query
     * SpEL - EntityName used instead of Applicant. It replaces the entityName with the domain type of the repository automatically
     * @Param :application_situation - Bind parameters used
     */
    @Query("FROM #{#entityName} WHERE application_situation= :application_situation")
    List<Applicant> findByStatus (@Param("application_situation") String aStatus);


    @Transactional
    @Modifying
    @Query("UPDATE #{#entityName} SET application_situation=?1 WHERE application_situation=?2 AND applicantID=?3")
   void updateStatus(String newStatus, String oldStatus, Long applicantID);

    @Transactional
    @Modifying
    @Query("UPDATE Applicant SET comments=?2")
    void save(Applicant applicant, String comments);



//    @Query(value= "SELECT ")


    Optional<Applicant> findById(Long id);
    
    Optional<Applicant> findFirstByUser(User user);

}
