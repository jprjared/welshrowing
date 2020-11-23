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
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long athlete_id;

    private String first_name;

    private String last_name;

    private Integer contact_number;

    private String dateOfBirth;

    private Boolean gender;

    private String address;

    private String town;

    private String county;

    private String postcode;

    private String country;

}
