package com.team1.welshrowing.repository;

import ch.qos.logback.core.joran.conditional.ThenAction;
import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Repository
public class ApplicantRepoImpl implements ApplicantRepo {

    private final ApplicantRepoJPA repository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public ApplicantRepoImpl(ApplicantRepoJPA repository) { this.repository = repository; }

    public Optional<Applicant> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void saveApplicant(Applicant applicant) {
        repository.save(applicant);
    }

    @Override
    public void updateApplicantStatus(Applicant applicant, String status) {
        Applicant applicantToUpdate = repository.getOne(applicant.getApplicantId());
        applicantToUpdate.setApplication_situation(status);
        repository.save(applicantToUpdate);
    }

    @Override
    public void ApplicantUpdateByStatus(String newStatus, String oldStatus, Long applicantID) {
        repository.updateStatus(newStatus, oldStatus, applicantID);
    }

    @Override
    public List<Applicant> ApplicantFindByStatus(String aStatus) {
        return repository.findByStatus(aStatus);
    }

    @Override
    public void sendEmailStatus(Applicant applicant) {

        String firstName = applicant.getFirstName();
        String lastName = applicant.getLastName();
        String emailTo = applicant.getUser().getEmail();
        String status = applicant.getApplication_situation();
        String accepted = "Congratulations " + firstName + ", " + "\n" + "\n" + "Welsh Rowing team is excited to announce that your application has been " + status.toLowerCase();
        String rejected = "Hello " + firstName + ", " + "\n" + "\n" + "We are sorry to inform you that your application has been " + status.toLowerCase();
        String mailSubject = " Welsh Rowing - Application Status";

        //Create an email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailTo);
        message.setSubject(mailSubject);

        if (applicant.getApplication_situation()=="Accepted") {
            message.setText(accepted);

        } else {
            message.setText(rejected);
        }

        //Send mail
        mailSender.send(message);
    }
}


