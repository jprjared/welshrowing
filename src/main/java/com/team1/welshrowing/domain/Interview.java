package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OrderBy

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
