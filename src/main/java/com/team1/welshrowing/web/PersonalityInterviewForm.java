package com.team1.welshrowing.web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


//All below are personality traits from 1 to 10

public class PersonalityInterviewForm {
    private String Name;

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
