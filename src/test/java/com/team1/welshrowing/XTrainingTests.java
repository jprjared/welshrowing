package com.team1.welshrowing;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.PersonalityInterview;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;
import com.team1.welshrowing.repository.PersonalityInterviewRepoJPA;
import com.team1.welshrowing.repository.XTrainingRepoJPA;
import com.team1.welshrowing.service.UserCreateService;
import com.team1.welshrowing.service.UserReadService;
import com.team1.welshrowing.service.XTrainingCreateService;
import com.team1.welshrowing.service.XTrainingReadService;
import org.apache.commons.lang3.time.DateParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import java.sql.Date;
import java.text.DateFormat;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class XTrainingTests {

    @Autowired
    XTrainingCreateService xTrainingCreateService;

    @Autowired
    XTrainingReadService xTrainingReadService;

    @Autowired
    UserReadService userReadService;

    @Autowired
    UserCreateService userCreateService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void CreateXTrainingForm() {

        User newUser = new User();
        newUser.setUserName("Ryan");
        newUser.setRoles("ATHLETE");
        newUser.setEmail("ryan@ryan.com");
        newUser.setPassword("pass");
        newUser.setUserId(1L);
        userCreateService.addUser(newUser);

        XTraining newXTraining = new XTraining();
        newXTraining.setXTrainingId(1L);
        newXTraining.setTypeOfTraining("Concept II / Watt Bike");
        newXTraining.setTotalTimeOfTraining(80);
        newXTraining.setTotalDistanceOfTraining(1600);
        newXTraining.setUser(newUser);


        xTrainingCreateService.addXTraining(newXTraining);

       Assertions.assertEquals("Concept II / Watt Bike",newXTraining.getTypeOfTraining());
    }

    @Test
    public void GetLatestXTraining() {
        User newUser = new User();
        newUser.setUserName("Ryan");
        newUser.setRoles("ATHLETE");
        newUser.setEmail("ryan@ryan.com");
        newUser.setPassword("pass");
        userCreateService.addUser(newUser);

        XTraining newXTraining = new XTraining();
        newXTraining.setXTrainingId(1L);
        newXTraining.setDateTime(Date.valueOf("2020-12-6"));
        newXTraining.setTypeOfTraining("Concept II / Watt Bike");
        newXTraining.setTotalTimeOfTraining(80);
        newXTraining.setTotalDistanceOfTraining(1600);
        newXTraining.setUser(newUser);
        xTrainingCreateService.addXTraining(newXTraining);

        XTraining newXTraining1 = new XTraining();
        newXTraining.setXTrainingId(2L);
        newXTraining.setDateTime(Date.valueOf("2020-12-4"));
        newXTraining.setTypeOfTraining("Swimming");
        newXTraining.setTotalTimeOfTraining(80);
        newXTraining.setTotalDistanceOfTraining(1600);
        newXTraining1.setUser(newUser);
        xTrainingCreateService.addXTraining(newXTraining1);

        Assertions.assertEquals("Concept II / Watt Bike",xTrainingReadService.getLastXTraining(newUser).get().getTypeOfTraining());

    }
}
