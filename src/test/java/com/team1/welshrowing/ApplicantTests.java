package com.team1.welshrowing;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.Feedback;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.ApplicantRepoJPA;
import com.team1.welshrowing.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
    private FeedbackCreateService feedbackCreateService;

    @Autowired
    private FeedbackReadService feedbackReadService;

    @Autowired
    private MockMvc mockMvc;

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

    @Test
    @WithUserDetails("coach")
    public void create_feedback_for_applicant() throws Exception {

        Long aGivenId = 1L;
        String aFeedback = "This is the reason";
        String aFile = "file.txt";

        Feedback theFeedback = new Feedback();
        Applicant theApplicant = new Applicant();

        theApplicant.setApplicantId(aGivenId);
        theFeedback.setFeedbackId(aGivenId);
        theFeedback.setMessage(aFeedback);
        theFeedback.setFile(aFile);

        applicantCreateService.addApplicant(theApplicant);
        feedbackCreateService.addFeedback(theFeedback);


        // Check that the feedback exists
        Assertions.assertEquals(aFeedback,feedbackReadService.findById(aGivenId).get().getMessage());

        mockMvc
                .perform(post("/coach/applicant/feedback/" + aGivenId))
                .andDo(print())
                .andExpect(status().is(403));

        // Check that feedback is displayed on the page
        Assertions.assertEquals(aFile,feedbackReadService.findById(aGivenId).get().getFile());
    }

    @Test
    @WithUserDetails("coach")
    public void create_an_applicant_and_pass() throws Exception {

        Long aGivenId = 1L;
        String aStatusBefore = "Accepted";
        String aStatusAfter = "Passed";

        Applicant theApplicant = new Applicant();

        theApplicant.setApplicantId(aGivenId);
        theApplicant.setApplication_situation("Accepted");

        applicantCreateService.addApplicant(theApplicant);



        // Check that the feedback exists
        Assertions.assertEquals(aStatusBefore,applicantReadService.findById(aGivenId).get().getApplication_situation());

        mockMvc
                .perform(post("/coach/applicant/pass/" + aGivenId))
                .andDo(print())
                .andExpect(status().is(403));

        String statusAfter = theApplicant.getApplication_situation();
        // Check that feedback is displayed on the page
        Assertions.assertEquals(statusAfter,applicantReadService.findById(aGivenId).get().getApplication_situation());
    }

    @Test
    @WithUserDetails("coach")
    public void create_an_applicant_and_fail() throws Exception {

        Long aGivenId = 1L;
        String aStatusBefore = "Accepted";
        String aStatusAfter = "Failed";

        Applicant theApplicant = new Applicant();

        theApplicant.setApplicantId(aGivenId);
        theApplicant.setApplication_situation("Accepted");

        applicantCreateService.addApplicant(theApplicant);



        // Check that the feedback exists
        Assertions.assertEquals(aStatusBefore,applicantReadService.findById(aGivenId).get().getApplication_situation());

        mockMvc
                .perform(post("/coach/applicant/fail/" + aGivenId))
                .andDo(print())
                .andExpect(status().is(403));

        String statusAfter = theApplicant.getApplication_situation();
        // Check that feedback is displayed on the page
        Assertions.assertEquals(statusAfter,applicantReadService.findById(aGivenId).get().getApplication_situation());
    }
}
