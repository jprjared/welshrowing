package com.team1.welshrowing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class PersonalityInterview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OrderBy

    private Integer InterviewId;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

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
