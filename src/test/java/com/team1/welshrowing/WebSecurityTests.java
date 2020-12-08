package com.team1.welshrowing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WebSecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homepageShouldNotRequireAuthentication() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());

        this.mockMvc
                .perform(get("/login"))
                .andExpect(status().isOk());
    }


    @Test
    public void resourcesShouldLoadWithoutAuthentication() throws Exception {
        this.mockMvc
                .perform(get("/css/main.css"))
                .andExpect(status().isOk());
        this.mockMvc
                .perform(get("/assets/logo.png"))
                .andExpect(status().isOk());
        this.mockMvc
                .perform(get("/css/login.css"))
                .andExpect(status().isOk());

    }

    @Test
    public void dashboardRedirectsWhenNotLoggedIn() throws Exception {
        this.mockMvc
                .perform(get("/athlete/dashboard"))
                .andExpect(status().is(302));
    }

    @Test
    @WithMockUser(username="coach", password="password", roles="COACH")
    public void dashboardRedirectsWhenLoggedInWithoutRequiredRole() throws Exception {
        // Only athletes can access this endpoint, not coaches
        this.mockMvc
                .perform(get("/athlete/dashboard"))
                .andExpect(status().is(403));
    }

    @Test
    @WithUserDetails("athlete")
    public void athleteDashboardIsOkWhenAccessedByAthlete() throws Exception {
        this.mockMvc
                .perform(get("/athlete/dashboard"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("athlete")
    public void signUpFormIsOkWhenAccessedByAthlete() throws Exception {
        this.mockMvc
                .perform(get("/register"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("athlete")
    public void applicationFormIsOkWhenAccessedByAthlete() throws Exception {
        this.mockMvc
                .perform(get("/register/application"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("coach")
    public void coachDashboardIsOkWhenAccessedByCoach() throws Exception {
        this.mockMvc
                .perform(get("/coach/dashboard"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("coach")
    public void coachAllApplicantsIsOkWhenAccessedByCoach() throws Exception {
        this.mockMvc
                .perform(get("/allApplicants"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("coach")
    public void coachAcceptedApplicantsIsOkWhenAccessedByCoach() throws Exception {
        this.mockMvc
                .perform(get("/application/status"))
                .andExpect(status().isOk());
    }
}
