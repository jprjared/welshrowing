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

public class PhysicalTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TestId;

    private Long applicantId;

    //    private Long userId;

    //    private Long coachId;

    private String Coachtesting;

    private String Dateoftest;




//  Athlete Information

    private String Nameofathlete;

    private String Homephonenumber;

//    School/Club/University
    private String Schoolorcluboruniversity;




//    Parent/Guardian Details
    private String Nameofparent;

    private String Relationshiptoathlete;

    private String Contactphone;

    private String Emailofguardian;




//    Anthropometry
//    athletes standing height in cm
    private Integer Standingheight;

//    Athlete weight in kg
    private Integer Weight;

//    Athletes arm span in cm
    private Integer Armspan;




//    Concept | Dyno

//    Any injuries that will effect the testing?
    private String Injuries;


//    Best legpress for 3 reps
    private Integer Legpress;

//    Best armpress for 3 reps
    private Integer Armpress;

//    Best armpull for 3 reps
    private Integer Armpull;

//    Best armpull for 15 reps
    private Integer Armpullmax;




//    Schwinn

//    Athletes score
    private Integer Score;

//    Coach observations can be tick box or text?
    private String Observations;




//    Core and flexibility

//    The athletes basic flexibility score drop down box to choose from red amber green
    private String Basicscore;

    private String Notesonscore;

//    Choose from red amber of green
    private String Flexibility;

    private String Notesonflexibility;



//    Training History

    private String Previoussports;

//    Number of months training last year
    private String nummonthstraining;

//    Number of training session per week including competitions
    private String numofsessionperweek;

//    Number of endurance sessions per week
    private String numofendurancesessions;

//    Number of strength sessions per week
    private String numofstrengthsessions;

//    Numbers of years training at this level
    private String numofyearstraining;


//    Follow up

//    Should me multiple choice
    private String NextStage;







}
