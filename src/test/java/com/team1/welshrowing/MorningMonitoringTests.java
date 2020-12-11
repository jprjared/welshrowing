package com.team1.welshrowing;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.service.MorningMonitoringCreateService;
import com.team1.welshrowing.service.MorningMonitoringReadService;
import com.team1.welshrowing.service.UserCreateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MorningMonitoringTests {

    @Autowired
    private UserCreateService userCreateService;

    @Autowired
    private MorningMonitoringReadService morningMonitoringReadService;

    @Autowired
    private MorningMonitoringCreateService morningMonitoringCreateService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void user_has_completed_morning_monitoring() throws Exception {

        User newUser = new User();
        newUser.setUserName("Ryan");
        newUser.setRoles("ATHLETE");
        newUser.setEmail("ryan@ryan.com");
        newUser.setPassword("pass");
        userCreateService.addUser(newUser);

        MorningMonitoring morningMonitoring = new MorningMonitoring();
        morningMonitoring.setUser(newUser);
        morningMonitoring.setDateTime(new Date());
        morningMonitoring.setPerceivedMentalState(5);

        morningMonitoringCreateService.addMorningMonitoring(morningMonitoring);

        // Check if this user has completed morning monitoring today
        Assertions.assertTrue(morningMonitoringReadService.hasCompletedMorningMonitoringToday(newUser));

    }

    @Test
    public void user_has_not_completed_morning_monitoring() throws Exception {

        User newUser = new User();
        newUser.setUserName("Ryan");
        newUser.setRoles("ATHLETE");
        newUser.setEmail("ryan@ryan.com");
        newUser.setPassword("pass");
        userCreateService.addUser(newUser);

        // Check if this user has completed morning monitoring today
        Assertions.assertFalse(morningMonitoringReadService.hasCompletedMorningMonitoringToday(newUser));

    }

    @Test
    public void api_returns_morning_monitoring_response() throws Exception {

        User newUser = new User();
        newUser.setUserName("Ryan");
        newUser.setRoles("ATHLETE");
        newUser.setEmail("ryan@ryan.com");
        newUser.setPassword("pass");
        userCreateService.addUser(newUser);

        MorningMonitoring morningMonitoring = new MorningMonitoring();
        morningMonitoring.setUser(newUser);
        morningMonitoring.setDateTime(new Date());
        morningMonitoring.setPerceivedMentalState(5);

        morningMonitoringCreateService.addMorningMonitoring(morningMonitoring);

        mockMvc
                .perform(get("/api/morning-monitoring/" + newUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"perceivedMentalState\":5")));

    }

}
