package com.team1.welshrowing;

import com.team1.welshrowing.domain.PhysicalTest;
import com.team1.welshrowing.repository.PhysicalTestRepoJPA;
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
public class PhysicalTestingTests {

    @Autowired
    private PhysicalTestRepoJPA physicalTestRepo;

    //    Allows to use EntityManager in tests
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void Createphysicaltestresults1() {
        PhysicalTest newPhysicalTest = new PhysicalTest();
        newPhysicalTest.setCoachtesting("James");
        newPhysicalTest.setDateoftest("23/06/2021");

//        Athlete Info
        newPhysicalTest.setNameofathlete("Jac");
        newPhysicalTest.setDOB("23/06/2001");
        newPhysicalTest.setGender("Male");
        newPhysicalTest.setEmail("Jac@Test.com");
        newPhysicalTest.setHomeaddress("7 Maes Clwyd");
        newPhysicalTest.setUniversityaddress("120 Miskin Street");
        newPhysicalTest.setMobilephonenumber("07572382939");
        newPhysicalTest.setHomephonenumber("01492329392");
        newPhysicalTest.setSchoolorcluboruniversity("University");

//        Parent Info
        newPhysicalTest.setNameofparent("Greg");
        newPhysicalTest.setRelationshiptoathlete("Dad");
        newPhysicalTest.setContactphone("07492930201");
        newPhysicalTest.setEmailofguardian("Greg@test.com");

//        Anthropometry
        newPhysicalTest.setStandingheight(160);
        newPhysicalTest.setWeight(80);
        newPhysicalTest.setArmspan(10);

//        Concept | Dyno
        newPhysicalTest.setInjuries("None");
        newPhysicalTest.setLegpress(200);
        newPhysicalTest.setArmpress(100);
        newPhysicalTest.setArmpull(80);
        newPhysicalTest.setArmpullmax(50);


//        Schwinn
        newPhysicalTest.setScore(100);
        newPhysicalTest.setObservations("Very Good Candidate");

//        Core and flexibility
        newPhysicalTest.setBasicscore(50);
        newPhysicalTest.setNotesonscore("Good Core");
        newPhysicalTest.setFlexibility(39);
        newPhysicalTest.setNotesonflexibility("Not so flexible");

//        Training History
        newPhysicalTest.setPrevioussports("Tennis");
        newPhysicalTest.setNummonthstraining(10);
        newPhysicalTest.setNumofsessionperweek(3);
        newPhysicalTest.setNumofendurancesessions(1);
        newPhysicalTest.setNumofstrengthsessions(2);
        newPhysicalTest.setNumofyearstraining(1);

//        follow up
        newPhysicalTest.setNextStage("Past with flying Colours");


        physicalTestRepo.save(newPhysicalTest);

        PhysicalTest savedPhysicalTest = physicalTestRepo.save(newPhysicalTest);

        PhysicalTest existingPhysicalTest = entityManager.find(PhysicalTest.class, savedPhysicalTest.getTestId());

        assertThat(existingPhysicalTest.getTestId()).isEqualTo(newPhysicalTest.getTestId());

    }

}
