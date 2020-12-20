package com.team1.welshrowing;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.RPE;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.RPERepoJPA;
import com.team1.welshrowing.service.MorningMonitoringCreateService;
import com.team1.welshrowing.service.MorningMonitoringReadService;
import com.team1.welshrowing.service.RPEReadService;
import com.team1.welshrowing.service.UserCreateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
public class RPETests {

    private WebDriver webDriver;

    @Autowired
    private UserCreateService userCreateService;

    @Autowired
    private RPERepoJPA rpeRepo;

    @Autowired
    private RPEReadService rpeReadService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void api_returns_morning_monitoring_response() throws Exception {

        User newUser = new User();
        newUser.setUserName("Jac");
        newUser.setRoles("ATHLETE");
        newUser.setEmail("test@test.com");
        newUser.setPassword("pass");
        userCreateService.addUser(newUser);

        RPE rpe = new RPE();
        rpe.setUser(newUser);
        rpe.setDateTime(new Date());
        rpe.setTypeofSession("Swimming");
        rpe.setSessionDuration(5);
        rpe.setRpe(5);

        rpeRepo.save(rpe);

        mockMvc
                .perform(get("/api/RPE-form/" + newUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"rpe\":5")));

    }

    @Test
    public void api_returns_latest_rpe_response() throws Exception {

        User newUser = new User();
        newUser.setUserName("Ryan");
        newUser.setRoles("ATHLETE");
        newUser.setEmail("ryan@ryan.com");
        newUser.setPassword("pass");
        userCreateService.addUser(newUser);

        RPE rpe = new RPE();
        rpe.setUser(newUser);
        rpe.setDateTime(new Date());
        rpe.setRpe(5);

        rpeRepo.save(rpe);

        RPE rpe2 = new RPE();
        rpe2.setUser(newUser);
        rpe2.setDateTime(new Date());
        rpe2.setRpe(8);

        rpeRepo.save(rpe2);

        mockMvc
                .perform(get("/api/RPE-form/" + newUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"rpe\":5")));

    }

}
