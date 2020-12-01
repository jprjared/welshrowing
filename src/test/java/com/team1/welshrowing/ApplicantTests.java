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
        newApplicant.setUserName("Sotiris123");
        newApplicant.setFirstName("Sotiris");
        newApplicant.setLastName("Yiallourides");
        newApplicant.setPhoneNumber("99355349");
        newApplicant.setAddress("Agiou Genadiou");
        newApplicant.setPostcode("cf 10 3fr");
        newApplicant.setCollege_university("Cardiff University");
        newApplicant.setDob("03/09/1932");
        newApplicant.setHeight(1.23);
        newApplicant.setGender("Male");
        newApplicant.setParentEmail("dwadf@gmail.com");
        newApplicant.setPassportHolder("No");
        newApplicant.setWhereDidHear("No where");
        newApplicant.setApplication_situation("Accepted");
        newApplicant.setCoach("Emily");

        applicantRepo.save(newApplicant);

        Applicant savedApplicant = applicantRepo.save(newApplicant);

        Applicant existApplicant = entityManager.find(Applicant.class, savedApplicant.getApplicantId());

        assertThat(existApplicant.getApplicantId()).isEqualTo(newApplicant.getApplicantId());
    }
}
