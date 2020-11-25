package com.team1.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantForm {

    private Long applicant_id;

    private String user_name;

    private String email;

    private String all_other_info;

    private String application_situation;

    private String coach;
}
