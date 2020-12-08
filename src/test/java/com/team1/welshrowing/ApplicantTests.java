package com.team1.welshrowing;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.Interview;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.InterviewRepoJPA;
import com.team1.welshrowing.service.ApplicantCreateService;
import com.team1.welshrowing.service.ApplicantEmailService;
import com.team1.welshrowing.service.ApplicantReadService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicantTests {

    @Autowired
    private ApplicantCreateService applicantCreateService;

    @Autowired
    private ApplicantReadService applicantReadService;

    @Autowired
    private ApplicantEmailService applicantEmailService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InterviewRepoJPA interviewRepo;

    @Test
    @WithUserDetails("coach")
    public void create_an_applicant_and_find_them() throws Exception {

        Long aGivenId = 1L;
        String aGivenName = "Ryan";

        Applicant theApplicant = new Applicant();
        theApplicant.setApplicantId(aGivenId);
        theApplicant.setFirstName(aGivenName);
        applicantCreateService.addApplicant(theApplicant);

        // Check that the applicant exists and IDs match
        Assertions.assertEquals(theApplicant.getApplicantId(), applicantReadService.findById(aGivenId).get().getApplicantId());

        // Check applicant is displayed on the page
        this.mockMvc
                .perform(get("/coach/applicant/" + aGivenId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(aGivenName)));

        Interview interview = new Interview();
        interview.setApplicantId(aGivenId);
        interview.setAspirations("To pass all tests successfully");

        interviewRepo.save(interview);

        // Check interview results are displayed
        this.mockMvc
                .perform(get("/coach/applicant/" + aGivenId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("To pass all tests successfully")));

    }

    @Test
    @WithUserDetails("coach")
    public void create_applicant_and_find_user_email() throws Exception {

        Long aGivenId = 1L;
        String aGivenEmail = "test@test.com";

        Applicant theApplicant = new Applicant();
        User theUser = new User();
        theApplicant.setApplicantId(aGivenId);
        theUser.setEmail(aGivenEmail);
        applicantCreateService.addApplicant(theApplicant);

        // Check that the applicant exists and email matches with User table
        Assertions.assertEquals(aGivenEmail,applicantReadService.findById(aGivenId).get().getUser().getEmail());

        mockMvc
                .perform(post("/coach/applicant/accept/" + aGivenId))
                .andDo(print())
                .andExpect(status().is(403));

        Assertions.assertEquals("Accepted",applicantReadService.findById(aGivenId).get().getApplication_situation());
    }
}
