package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class PersonalityInterview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OrderBy

    private Integer InterviewId;

    //    private Long userId;

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
