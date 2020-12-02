package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * Represents an Applicant.
 */
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * A unique suffix that will identify the athlete.
     */
    private Long applicantId;

    /**
     * The user name of the applicant.
     */
    private String userName;

    /**
     * The first name of the applicant.
     */
    private String email;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String address;
    private String postcode;
    private String college_uni;
    private String dob;
    private int height;
    private String gender;
    private String parentgurdian_email;
    private String passport_holder;
    private Boolean previouslytested = false;
    private String wheredidhear;
//    private String firstName;

    /**
     * The last name of the applicant.
     */
//    private String lastName;

    /**
     * The phone number of the applicant.
     */
//    private String phoneNumber;

    /**
     * The address of the applicant.
     */
//    private String address;

    /**
     * The postcode of the applicant.
     */
//    private String postcode;

    /**
     * The user name of the applicant.
     */
//    private String college_university;

    /**
     * The date of birth of the applicant.
     */
//    private String dob;

    /**
     * The height of the applicant.
     */
//    private Double height;

    /**
     * The gender of the applicant.
     */
//    private String gender;

    /**
     * The parent's email of the applicant.
     */
//    private String parentEmail;

    /**
     * The passport of the applicant.
     */
//    private String passportHolder;

    /**
     * Has the applicant been previously tested before.
     */
//    private Boolean isPreviouslyTested = false;

    /**
     * From where the applicant have heard about the club.
     */
//    private String whereDidHear;

}
