package com.team1.welshrowing.service;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.Feedback;
import com.team1.welshrowing.repository.FeedbackRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackReadService {

    private final FeedbackRepo repository;

    public FeedbackReadService(FeedbackRepo repository) {
        this.repository = repository;
    }

    public Optional<Feedback> findById(Long id) { return repository.findById(id); }
}
