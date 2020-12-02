package com.team1.welshrowing;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.repository.ApplicantRepoJPA;
import com.team1.welshrowing.service.ApplicantCreateService;
import com.team1.welshrowing.service.ApplicantReadService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApplicantTests {

    @Autowired
    private ApplicantCreateService applicantCreateService;

    @Autowired
    private ApplicantReadService applicantReadService;

    @Test
    public void create_an_applicant_and_find_them() {
        Applicant theApplicant = new Applicant();
        theApplicant.setApplicantId((long) 1);
        applicantCreateService.addApplicant(theApplicant);
        Assertions.assertEquals(theApplicant.getApplicantId(), applicantReadService.findById((long) 1).get().getApplicantId());
    }
}