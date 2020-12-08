package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ApplicationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantId;

    private Double height;

    private String dob;

    private String comments;

    private String result;

    private String firstName;

    private String lastName;

//    @OneToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(name = "applicantId")
//    private Applicant applicant;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "result")
//    private Applicant applicant1;
}
