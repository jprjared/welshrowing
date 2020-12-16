package com.team1.welshrowing;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.Feedback;
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

        String aGivenName = "Ryan";

        Applicant theApplicant = new Applicant();
        theApplicant.setFirstName(aGivenName);
        theApplicant.setGender("Male");
        theApplicant.setCollegeOrUniversityName("Cardiff University");
        theApplicant.setHeight(190.0);
        theApplicant.setAddress("Northgate");
        theApplicant.setWhereDidHear("Facebook");
        theApplicant.setDob("2005-05-01");
        theApplicant.setLastName("Davies");
        theApplicant.setPhoneNumber("00792312341");
        theApplicant.setPostcode("CF10 3FG");
        applicantCreateService.addApplicant(theApplicant);

        // Check that the applicant exists and IDs match
        Assertions.assertEquals(theApplicant.getApplicantId(), applicantReadService.findById(theApplicant.getApplicantId()).get().getApplicantId());

        // Check applicant is displayed on the page
        this.mockMvc
                .perform(get("/coach/applicant/" + theApplicant.getApplicantId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(aGivenName)));
    }

    @Test
    @WithUserDetails("coach")
    public void create_feedback_for_applicant() throws Exception {

        String aFeedback = "This is the reason";
        String aFile = "file.txt";

        Feedback theFeedback = new Feedback();
        Applicant theApplicant = new Applicant();

        theFeedback.setMessage(aFeedback);
        theFeedback.setFile(aFile);

        applicantCreateService.addApplicant(theApplicant);
        feedbackCreateService.addFeedback(theFeedback);


        // Check that the feedback exists
        Assertions.assertEquals(aFeedback,feedbackReadService.findById(theFeedback.getFeedbackId()).get().getMessage());

        mockMvc
                .perform(post("/coach/applicant/feedback/" + theApplicant.getApplicantId()))
                .andDo(print())
                .andExpect(status().is(403));

        // Check that feedback is displayed on the page
        Assertions.assertEquals(aFile,feedbackReadService.findById(theFeedback.getFeedbackId()).get().getFile());
    }

    @Test
    @WithUserDetails("coach")
    public void create_an_applicant_and_pass() throws Exception {

        String aStatusBefore = "Accepted";
        String aStatusAfter = "Passed";

        Applicant theApplicant = new Applicant();
        theApplicant.setApplication_situation("Accepted");
        applicantCreateService.addApplicant(theApplicant);



        // Check that the feedback exists
        Assertions.assertEquals(aStatusBefore,applicantReadService.findById(theApplicant.getApplicantId()).get().getApplication_situation());

        mockMvc
                .perform(post("/coach/applicant/pass/" + theApplicant.getApplicantId()))
                .andDo(print())
                .andExpect(status().is(403));

        String statusAfter = theApplicant.getApplication_situation();
        // Check that feedback is displayed on the page
        Assertions.assertEquals(statusAfter,applicantReadService.findById(theApplicant.getApplicantId()).get().getApplication_situation());
    }

    @Test
    @WithUserDetails("coach")
    public void create_an_applicant_and_fail() throws Exception {

        String aStatusBefore = "Accepted";
        String aStatusAfter = "Failed";

        Applicant theApplicant = new Applicant();
        theApplicant.setApplication_situation("Accepted");

        applicantCreateService.addApplicant(theApplicant);



        // Check that the feedback exists
        Assertions.assertEquals(aStatusBefore,applicantReadService.findById(theApplicant.getApplicantId()).get().getApplication_situation());

        mockMvc
                .perform(post("/coach/applicant/fail/" + theApplicant.getApplicantId()))
                .andDo(print())
                .andExpect(status().is(403));

        String statusAfter = theApplicant.getApplication_situation();
        // Check that feedback is displayed on the page
        Assertions.assertEquals(statusAfter,applicantReadService.findById(theApplicant.getApplicantId()).get().getApplication_situation());
    }

}
