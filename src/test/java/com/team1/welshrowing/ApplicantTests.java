package com.team1.welshrowing;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.Feedback;
import com.team1.welshrowing.domain.Interview;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.repository.InterviewRepoJPA;
import com.team1.welshrowing.service.ApplicantCreateService;
import com.team1.welshrowing.service.ApplicantEmailService;
import com.team1.welshrowing.service.ApplicantReadService;
import com.team1.welshrowing.service.FeedbackCreateService;
import com.team1.welshrowing.service.FeedbackReadService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        theApplicant.setUser(theUser);
        theUser.setEmail(aGivenEmail);
        applicantCreateService.addApplicant(theApplicant);

        // Check that the applicant exists and email matches with User table
        Assertions.assertEquals(aGivenEmail,applicantReadService.findById(aGivenId).get().getUser().getEmail());

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
