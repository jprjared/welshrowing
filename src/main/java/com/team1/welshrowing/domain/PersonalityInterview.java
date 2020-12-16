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

public class PersonalityInterview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer InterviewId;

    private Long applicantId;

    //    private Long userId;

    private Integer Risktaking;

    private Integer Bravery;

    private Integer Resilience;

    private Integer Hardworking;

    private Integer Confidence;

    private Integer OpenMindedness;

    private Integer Onoffswitch;

    private Integer Lightheartedness;

    private Integer Selfdiscipline;

    private Integer Abilitytoworkwithothers;

    private Integer Competitiveness;
}
