package com.team1.welshrowing;

import com.team1.welshrowing.domain.Interview;
import com.team1.welshrowing.repository.InterviewRepoJPA;
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
public class InterviewTests {

    @Autowired
    private InterviewRepoJPA interviewRepo;

    //    Allows to use EntityManager in tests
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void Createinterviewresults1() {
        Interview newInterview = new Interview();
        newInterview.setElitesportdeff("A sport at the highest level");
        newInterview.setSportexp("Have been rowing for years");
        newInterview.setAspirations("Olympic Medalist");
        newInterview.setWhatcanyoubring("A new fighting spirit to the team");
        newInterview.setCoachRole("Coach");
        newInterview.setPossiblebarriers("Still in University so have to balance it with uni work");
        newInterview.setProcessoroutcome(8);
        newInterview.setIntrinsicorextrinsic(7);
        newInterview.setProcessoroutcomecomp(4);
        newInterview.setIntrinsicorextrinsiccomp(7);
        newInterview.setOpportunitymeaning("It means the world as I can achieve my dreams of being the best at the sport");
        newInterview.setRolemodels("Sir Steve Redgrave because I saw him competing and made me want to compete");
        newInterview.setYourstory("Was born in South Wales moved to North wales ..... ");

        interviewRepo.save(newInterview);

        Interview savedInterview = interviewRepo.save(newInterview);

        Interview existingInterview = entityManager.find(Interview.class, savedInterview.getInterviewId());

        assertThat(existingInterview.getInterviewId()).isEqualTo(newInterview.getInterviewId());

    }

}
