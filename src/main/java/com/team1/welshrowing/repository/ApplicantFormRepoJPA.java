package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantFormRepoJPA extends JpaRepository<ApplicationForm, Long> {
}
