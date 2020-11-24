package com.team1.welshrowing;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.repository.ApplicantRepoJPA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ApplicantTests {

    @Autowired
    private ApplicantRepoJPA applicantRepo;

    //    Allows to use EntityManager in tests
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void CreateApplicant1() {
        Applicant newApplicant = new Applicant();
        newApplicant.setUser_name("Sotiris");
        newApplicant.setEmail("jacob@gmail.com");
        newApplicant.setAll_other_info("No info");
        newApplicant.setApplication_situation("Pending");
        newApplicant.setCoach("Jenny");

        applicantRepo.save(newApplicant);

        Applicant savedApplicant = applicantRepo.save(newApplicant);

        Applicant existApplicant = entityManager.find(Applicant.class, savedApplicant.getApplicant_id());

        assertThat(existApplicant.getApplicant_id()).isEqualTo(newApplicant.getApplicant_id());

    }
}
