package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
     * The first name of the applicant.
     */
    @ColumnTransformer(read = "cast(aes_decrypt(first_name, 'J9DVC?n(') as char(255))")
    private String firstName;

    /**
     * The last name of the applicant.
     */
    @ColumnTransformer(read = "cast(aes_decrypt(last_name, 'J9DVC?n(') as char(255))")
    private String lastName;

    /**
     * The phone number of the applicant.
     */
    @ColumnTransformer(read = "cast(aes_decrypt(phone_number, 'J9DVC?n(') as char(255))")
    private String phoneNumber;

    /**
     * The address of the applicant.
     */
    @ColumnTransformer(read = "cast(aes_decrypt(address, 'J9DVC?n(') as char(255))")
    private String address;

    /**
     * The postcode of the applicant.
     */
    @ColumnTransformer(read = "cast(aes_decrypt(postcode, 'J9DVC?n(') as char(255))")
    private String postcode;

    /**
     * The user name of the applicant.
     */
    @ColumnTransformer(read = "cast(aes_decrypt(college_or_university_name, 'J9DVC?n(') as char(255))")
    private String collegeOrUniversityName;

    /**
     * The date of birth of the applicant.
     */
    @ColumnTransformer(read = "cast(aes_decrypt(dob, 'J9DVC?n(') as char(255))")
    private String dob;

    /**
     * The height of the applicant.
     */
    private Double height;

    /**
     * The gender of the applicant.
     */
    private String gender;

    /**
     * The parent's email of the applicant.
     */
    @ColumnTransformer(read = "cast(aes_decrypt(parent_email, 'J9DVC?n(') as char(255))")
    private String parentEmail;

    /**
     * The passport of the applicant.
     */
    @ColumnTransformer(read = "cast(aes_decrypt(passport_holder, 'J9DVC?n(') as char(255))")
    private String passportHolder;

    /**
     * Has the applicant been previously tested before.
     */
    private Boolean isPreviouslyTested = false;

    /**
     * From where the applicant have heard about the club.
     */
    private String whereDidHear;

    /**
     * The situation of the application of the applicant.
     */
    private String application_situation = "Pending";

    /**
     * The coach of the applicant.
     */
    private String coach;

    /**
     * The comments inputted by the coach.
     */
    @ColumnTransformer(read = "cast(aes_decrypt(comments, 'J9DVC?n(') as char(255))")
    private String comments;
    /**
     * Linking User Object with User Object
     * One to One Relationship
     */

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId")
    private User user;

}
