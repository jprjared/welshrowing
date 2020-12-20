package com.team1.welshrowing;

import com.team1.welshrowing.domain.MorningMonitoring;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.service.MorningMonitoringCreateService;
import com.team1.welshrowing.service.MorningMonitoringReadService;
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
public class MorningMonitoringTests {

    private WebDriver webDriver;

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
        morningMonitoring.setOsmoticHeartRate(4);
        morningMonitoring.setPerceivedShape(4);
        morningMonitoring.setSleepQuantity(5.0);
        morningMonitoring.setSleepQuality(5);
        morningMonitoring.setDateTime(new Date());
        morningMonitoring.setPerceivedMentalState(5);
        morningMonitoring.setWakingHeartRate(5);
        morningMonitoring.setStandingHeartRate(5);

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
        morningMonitoring.setOsmoticHeartRate(4);
        morningMonitoring.setPerceivedShape(4);
        morningMonitoring.setSleepQuantity(5.0);
        morningMonitoring.setSleepQuality(5);
        morningMonitoring.setDateTime(new Date());
        morningMonitoring.setPerceivedMentalState(5);
        morningMonitoring.setWakingHeartRate(5);
        morningMonitoring.setStandingHeartRate(5);

        morningMonitoringCreateService.addMorningMonitoring(morningMonitoring);

        mockMvc
                .perform(get("/api/morning-monitoring/" + newUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"perceivedMentalState\":5")));

    }

    @Test
    public void api_returns_latest_morning_monitoring_response() throws Exception {

        User newUser = new User();
        newUser.setUserName("Ryan");
        newUser.setRoles("ATHLETE");
        newUser.setEmail("ryan@ryan.com");
        newUser.setPassword("pass");
        userCreateService.addUser(newUser);

        MorningMonitoring morningMonitoring = new MorningMonitoring();
        morningMonitoring.setUser(newUser);
        morningMonitoring.setOsmoticHeartRate(4);
        morningMonitoring.setPerceivedShape(4);
        morningMonitoring.setSleepQuantity(5.0);
        morningMonitoring.setSleepQuality(5);
        morningMonitoring.setDateTime(new Date());
        morningMonitoring.setPerceivedMentalState(5);
        morningMonitoring.setWakingHeartRate(5);
        morningMonitoring.setStandingHeartRate(5);

        morningMonitoringCreateService.addMorningMonitoring(morningMonitoring);

        MorningMonitoring morningMonitoring2 = new MorningMonitoring();
        morningMonitoring2.setUser(newUser);
        morningMonitoring2.setOsmoticHeartRate(4);
        morningMonitoring2.setPerceivedShape(4);
        morningMonitoring2.setSleepQuantity(5.0);
        morningMonitoring2.setSleepQuality(5);
        morningMonitoring2.setDateTime(new Date());
        morningMonitoring2.setPerceivedMentalState(10);
        morningMonitoring2.setWakingHeartRate(5);
        morningMonitoring2.setStandingHeartRate(5);

        morningMonitoringCreateService.addMorningMonitoring(morningMonitoring2);

        mockMvc
                .perform(get("/api/morning-monitoring/latest/" + newUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"perceivedMentalState\":10")));

    }

    //@Test
    public void submit_morning_monitoring_form_and_display_on_dashboard() throws InterruptedException {

        String driverPath = "bin/geckodriver";
        String os = System.getProperty("os.name");

        if (os.contains("Windows")) {
            driverPath = driverPath + ".exe";
        } // Else unix

        System.setProperty("webdriver.gecko.driver", driverPath);
        webDriver = new FirefoxDriver();

        // Go to login page and log in
        this.webDriver.get("http://localhost:" + Integer.toString(8080) + "/login");
        this.webDriver.findElement(By.id("username")).sendKeys("athlete");
        this.webDriver.findElement(By.id("password")).sendKeys("pass");
        this.webDriver.findElement(By.id("submit")).click();

        // Check that we are now on the dashboard
        Assertions.assertTrue(webDriver.findElement(By.cssSelector("main h1")).getText().contains("Dashboard"));

        // Go to morning monitoring form and complete the form
        this.webDriver.findElement(By.linkText("Complete Morning Monitoring")).click();

        this.webDriver.findElement(By.id("wakingHeartRate")).sendKeys("40");
        this.webDriver.findElement(By.id("standingHeartRate")).sendKeys("50");
        this.webDriver.findElement(By.id("sleepQuantity")).sendKeys("5");
        this.webDriver.findElement(By.id("submit")).click();

        // All submitted! We should now be redirected back to dashboard
        Assertions.assertTrue(webDriver.findElement(By.cssSelector("main h1")).getText().contains("Dashboard"));

        // Let's check that our values have been saved and displayed
        Assertions.assertTrue(this.webDriver.findElement(By.id("ohr-text")).getText().contains("10")); // Osmotic heart rate is standing minus waking heart rate
        Assertions.assertTrue(this.webDriver.findElement(By.id("whr-text")).getText().contains("40")); // Waking heart rate
        Assertions.assertTrue(this.webDriver.findElement(By.id("shape-text")).getText().contains("5")); // Default shape should be 5

    }

}
