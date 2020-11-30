package com.team1.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantForm {

    private Long applicant_id;

    private String userName;

    private String first_name;
    private String last_name;
    private String phone_number;
    private String address;
    private String postcode;
    private String college_uni;
    private String email;
    private Date dob;
    private int height;
    private String gender;
    private String parentgurdian_email;
    private String passport_holder;
    private String previouslytested;
    private String wheredidhear;



}
