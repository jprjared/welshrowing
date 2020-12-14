package com.team1.welshrowing;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.domain.XTraining;
import com.team1.welshrowing.service.UserCreateService;
import com.team1.welshrowing.service.UserReadService;
import com.team1.welshrowing.service.XTrainingCreateService;
import com.team1.welshrowing.service.XTrainingReadService;
import org.junit.*;

import org.apache.commons.lang3.time.DateParser;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void CreateValidator(){
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close(){
        validatorFactory.close();
    }

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

    @Test
    public void checkXTrainingValidation() throws Exception {

        User newUser = new User();
        newUser.setUserId(1L);
        newUser.setUserName("Ryan");
        newUser.setRoles("ATHLETE");
        newUser.setEmail("ryan@ryan.com");
        newUser.setPassword("pass");
        userCreateService.addUser(newUser);

        XTraining xtraining = new XTraining();
        xtraining.setUser(newUser);
        xtraining.setTotalTimeOfTraining(null);

        mockMvc
                .perform(post("/athlete/x-training"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void testEmail(){
        User newUser = new User();
        newUser.setUserId(1L);
        newUser.setUserName("Ryan");
        newUser.setRoles("ATHLETE");
        newUser.setEmail("ryan@ryan.com");
        newUser.setPassword("pass");
        userCreateService.addUser(newUser);

        XTraining xtraining = new XTraining();
        xtraining.setUser(newUser);
        xtraining.setDateOfTraining(null);
        xtraining.setTypeOfTraining("Swimming");
        xtraining.setTotalDistanceOfTraining(1500);
        xtraining.setXTrainingId(2L);
        xtraining.setTotalTimeOfTraining(1600);
        xTrainingCreateService.addXTraining(xtraining);

        Set<ConstraintViolation<XTraining>> violations = validator.validate(xtraining);

        assertEquals(violations.size(),1);

        ConstraintViolation<XTraining> violation = violations.iterator().next();
        assertEquals("This field should not be null", violation.getMessage());

    }
}
