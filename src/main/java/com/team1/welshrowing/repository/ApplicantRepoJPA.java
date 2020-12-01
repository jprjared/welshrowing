package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ApplicantRepoJPA extends CrudRepository<Applicant, Long> {
}
