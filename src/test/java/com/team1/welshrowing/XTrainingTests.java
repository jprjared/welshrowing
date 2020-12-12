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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.test.context.support.WithUserDetails;

import java.util.Date;

public class XTrainingTests {

    @Autowired
    XTrainingCreateService xTrainingCreateService;

    @Autowired
    XTrainingReadService xTrainingReadService;

    @Autowired
    UserReadService userReadService;

    @Autowired
    UserCreateService userCreateService;

    @Test
    public void CreateXTrainingForm() {

        User newUser = new User();
        newUser.setUserName("Ryan");
        newUser.setRoles("ATHLETE");
        newUser.setEmail("ryan@ryan.com");
        newUser.setPassword("pass");
        userCreateService.addUser(newUser);

        XTraining newXTraining = new XTraining();
        newXTraining.setXTrainingId(1L);
        newXTraining.setDateOfTraining("2020-12-11");
        newXTraining.setTypeOfTraining("Concept II / Watt Bike");
        newXTraining.setTotalTimeOfTraining(80);
        newXTraining.setTotalDistanceOfTraining(1600);
        newUser.setUserId(1L);

        xTrainingCreateService.addXTraining(newXTraining);

       Assertions.assertEquals(newUser,xTrainingReadService.findByUser(newXTraining.getUser()));
    }
}
