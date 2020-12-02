package com.team1.welshrowing;

import com.team1.welshrowing.domain.Interview;
import com.team1.welshrowing.domain.PersonalityInterview;
import com.team1.welshrowing.repository.InterviewRepoJPA;
import com.team1.welshrowing.repository.PersonalityInterviewRepoJPA;
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
public class PersonalityInterviewTests {

    @Autowired
    private PersonalityInterviewRepoJPA personalityInterviewRepo;

    //    Allows to use EntityManager in tests
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void Createpersonalityinterviewresults1() {
        PersonalityInterview newpersonalityInterview = new PersonalityInterview();
        newpersonalityInterview.setName("Jac");
        newpersonalityInterview.setRisktaking(2);
        newpersonalityInterview.setBravery(3);
        newpersonalityInterview.setResilience(4);
        newpersonalityInterview.setHardworking(2);
        newpersonalityInterview.setConfidence(6);
        newpersonalityInterview.setOpenMindedness(3);
        newpersonalityInterview.setOnoffswitch(8);
        newpersonalityInterview.setLightheartedness(9);
        newpersonalityInterview.setSelfdiscipline(3);
        newpersonalityInterview.setAbilitytoworkwithothers(2);
        newpersonalityInterview.setCompetitiveness(1);

        personalityInterviewRepo.save(newpersonalityInterview);

        PersonalityInterview savedPersonalityInterview = personalityInterviewRepo.save(newpersonalityInterview);

        PersonalityInterview existingPersonalityInterview = entityManager.find(PersonalityInterview.class, savedPersonalityInterview.getInterviewId());

        assertThat(existingPersonalityInterview.getInterviewId()).isEqualTo(existingPersonalityInterview.getInterviewId());

    }
}
