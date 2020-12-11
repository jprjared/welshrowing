package com.team1.welshrowing.repository;

import com.team1.welshrowing.domain.Applicant;
import com.team1.welshrowing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import java.io.File;
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
    public Optional<Applicant> findByUser(User user) {
        return repository.findByUser(user);
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
    public void updateApplicantComments(Applicant applicant, String comments) {
        Applicant applicant1 = repository.getOne(applicant.getApplicantId());
        applicant1.setComments(comments);
        repository.save(applicant1);
    }

    @Override
    public void ApplicantUpdateByStatus(String newStatus, String oldStatus, Long applicantID) {
        repository.updateStatus(newStatus, oldStatus, applicantID);
    }

    @Override
    public void ApplicantUpdateByComments(String newComments, String oldComments, Long applicantID) {
        repository.updateComments(newComments, oldComments, applicantID);
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
        String accepted = "Congratulations " + firstName + ", " + "\n" + "\n" + "Welsh Rowing team is excited to announce that your application has been " + status.toLowerCase() + "."
                        + "\n" + "\n" + "Regards," + "\n" + "\n" + "Welsh Rowing Team";
        String rejected = "Hello " + firstName + ", " + "\n" + "\n" + "We are sorry to inform you that your application has been " + status.toLowerCase() + "."
                        + "\n" + "\n" + "Regards," + "\n" + "\n" + "Welsh Rowing Team";

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

    @Override
    public void sendEmailPassFail(Applicant applicant) {

        String firstName = applicant.getFirstName();
        String lastName = applicant.getLastName();
        String emailTo = applicant.getUser().getEmail();
        String status = applicant.getApplication_situation();

        String passed = "Congratulations " + firstName + ", " + "\n" + "\n" + "Welsh Rowing team is excited to announce that you have " + status.toLowerCase() + " the tests."
                        + "\n" + "We look forward to following up with you about the 8 Week Programme" + "\n" + "and we will contact you very soon." + "\n" + "\n" + "Regards," + "\n" + "\n" + "Welsh Rowing Team";

        String failed = "Hello " + firstName + ", " + "\n" + "\n" + "We are sorry to inform you that you have " + status.toLowerCase() + " the tests."
                        + "\n" + "Thank you for taking part. We wish you all the best." + "\n" + "\n" + "Regards," + "\n" + "\n" + "Welsh Rowing Team";

        String mailSubject = " Welsh Rowing - Test Results";

        //Create an email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailTo);
        message.setSubject(mailSubject);

        if (applicant.getApplication_situation()=="Passed") {
            message.setText(passed);

        } else {
            message.setText(failed);
        }

        //Send mail
        mailSender.send(message);

    }


    @Override
    public void sendEmailFeedback(Applicant applicant, String feedback, String file) {

        String firstName = applicant.getFirstName();
        String lastName = applicant.getLastName();
        String emailTo = applicant.getUser().getEmail();
        String status = applicant.getApplication_situation();
        String document = file;
        String mailSubject = " Welsh Rowing - Feedback";
        String msg = "Hello " + firstName + "," + "\n" + "\n" + feedback
                     + "\n" + "\n" + "Regards," + "\n" + "\n" + "Welsh Rowing Team";


        //Create an email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailTo);
        message.setSubject(mailSubject);
        message.setText(msg);

    //Send mail
        mailSender.send(message);
    }

}


