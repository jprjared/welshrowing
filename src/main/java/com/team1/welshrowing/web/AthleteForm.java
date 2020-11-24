package com.team1.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AthleteForm {

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
