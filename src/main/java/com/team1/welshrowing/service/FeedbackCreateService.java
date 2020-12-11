package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.Feedback;
import com.team1.welshrowing.repository.ApplicantRepo;
import com.team1.welshrowing.repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackCreateService {

    private final FeedbackRepo repository;

    @Autowired
    public FeedbackCreateService(FeedbackRepo repository) {
        this.repository = repository;
    }

    public void addFeedback(Feedback feedback) {
        repository.saveFeedback(feedback);
    }

}
