package com.team1.welshrowing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OrderBy
    private Long InterviewId;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

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
