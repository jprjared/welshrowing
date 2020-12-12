package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Feedback;

import java.util.Optional;

public interface FeedbackRepo {

    void saveFeedback(Feedback feedback);

    Optional<Feedback> findById(Long Id);

}
