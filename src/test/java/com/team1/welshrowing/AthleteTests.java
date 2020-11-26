package com.team1.welshrowing;

import com.team1.welshrowing.domain.Athlete;
import com.team1.welshrowing.repository.AthleteRepoJPA;
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
    public class AthleteTests {

        @Autowired
        private AthleteRepoJPA athleteRepo;

        //    Allows to use EntityManager in tests
        @Autowired
        private TestEntityManager entityManager;

        @Test
        public void CreateAthlete1() {
            Athlete newAthlete = new Athlete();
            newAthlete.setFirst_name("Sotiris");
            newAthlete.setLast_name("Yiallourides");
            newAthlete.setContact_number(993492348);
            newAthlete.setDateOfBirth("3/2/1999");
            newAthlete.setGender(Boolean.getBoolean("M"));
            newAthlete.setAddress("Windsor Ln");
            newAthlete.setTown("Cardiff");
            newAthlete.setCounty("");
            newAthlete.setPostcode("CF10 3FG");
            newAthlete.setCountry("United Kingdom");

            athleteRepo.save(newAthlete);

            Athlete savedAthlete = athleteRepo.save(newAthlete);

            Athlete existAthlete = entityManager.find(Athlete.class, savedAthlete.getAthlete_id());

            assertThat(existAthlete.getAthlete_id()).isEqualTo(newAthlete.getAthlete_id());

        }
}
