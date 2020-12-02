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
 * Represents an Athlete.
 */
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * A unique suffix that will identify the athlete.
     */
    private Long athlete_id;

    /**
     * The first name of the athlete.
     */
    private String first_name;

    /**
     * The last name of the athlete.
     */
    private String last_name;

    /**
     * The contact number of the athlete.
     */
    private Integer contact_number;

    /**
     * The date of birth of the athlete.
     */
    private String dateOfBirth;

    /**
     * The gender of the athlete.
     */
    private Boolean gender;

    /**
     * The address of the athlete.
     */
    private String address;

    /**
     * The town of the athlete.
     */
    private String town;

    /**
     * The county of the athlete.
     */
    private String county;

    /**
     * The postcode of the athlete.
     */
    private String postcode;

    /**
     * The country of the athlete.
     */
    private String country;

//    public String getFirstName(){
//
//        return first_name;
//    }

}
