package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long InterviewId;

    private Long applicantId;

    private String elitesportdeff;

    private String Sportexp;

    private String Aspirations;

    private String Whatcanyoubring;

    private String CoachRole;

    private String Possiblebarriers;

    private Integer Processoroutcome;

    private Integer Intrinsicorextrinsic;

    private Integer Processoroutcomecomp;

    private Integer Intrinsicorextrinsiccomp;

    private String Opportunitymeaning;

    private String Rolemodels;

    private String Yourstory;


}
