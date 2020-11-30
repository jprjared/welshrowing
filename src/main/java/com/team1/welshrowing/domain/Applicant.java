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
    private Long applicant_id;

    /**
     * The user name of the applicant.
     */
    private String userName;

    /**
     * The email of the applicant.
     */
    private String email;

//    /**
//     * The information of the applicant.
//     */
//    private String all_other_info;
//
//    /**
//     * The situation of the application of the applicant.
//     */
//    private String application_situation;
//
//    /**
//     * The coach of the applicant.
//     */
//    private String coach;


    private Date date;

    private int height;
}
