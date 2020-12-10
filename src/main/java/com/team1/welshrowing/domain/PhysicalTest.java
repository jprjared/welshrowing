package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class PhysicalTest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OrderBy

    private Integer TestId;

    private Long applicantId;

    //    private Long userId;

    //    private Long coachId;

    private String Coachtesting;

    private String Dateoftest;




//  Athlete Information

    @NotNull (message = "This field cannot be null")
    @Pattern(regexp = "[A-Za-z]", message = "It must not contain any numbers or special characters")
    private String Nameofathlete;

    @NotNull (message = "This field cannot be null")
    private String Homephonenumber;

//    School/Club/University
    @NotNull (message = "This field cannot be null")
    private String Schoolorcluboruniversity;




//    Parent/Guardian Details
    @NotNull (message = "This field cannot be null")
    private String Nameofparent;

    @NotNull (message = "This field cannot be null")
    private String Relationshiptoathlete;

    @NotNull (message = "This field cannot be null")
    @Pattern(regexp = "[0][789][0-9]{9}", message = "It must not contain any letters")
    private String Contactphone;

    @NotNull (message = "This field cannot be null")
    @Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Please enter a valid email")
    private String Emailofguardian;




//    Anthropometry
//    athletes standing height in cm
    @NotNull (message = "This field cannot be null")
    @Min(value = 152, message = "Number should not be less than 152cm")
    @Max(value = 250, message = "Number should not be higher than 250cm")
    @Positive(message = "Number must be positive")
    private Integer Standingheight;

//    Athlete weight in kg
    @NotNull (message = "This field cannot be null")
    @Min(value = 30, message = "Number should not be less than 30")
    @Max(value = 200, message = "Number should not be higher than 200")
    @Positive(message = "Number must be positive")
    private Integer Weight;

//    Athletes arm span in cm
    @NotNull (message = "This field cannot be null")
    @Min(value = 152, message = "Number should not be less than 152cm")
    @Max(value = 250, message = "Number should not be higher than 250cm")
    @Positive(message = "Number must be positive")
    private Integer Armspan;




//    Concept | Dyno

//    Any injuries that will effect the testing?
    @NotNull (message = "This field cannot be null")
    @Pattern(regexp = "[A-Za-z]", message = "It must not contain any numbers or special characters")
    private String Injuries;


//    Best legpress for 3 reps
    @NotNull (message = "This field cannot be null")
    @Min(value = 50, message = "Number should not be less than 50")
    @Max(value = 300, message = "Number should not be higher than 300")
    @Positive(message = "Number must be positive")
    private Integer Legpress;

//    Best armpress for 3 reps
    @NotNull (message = "This field cannot be null")
    @Min(value = 20, message = "Number should not be less than 20")
    @Max(value = 150, message = "Number should not be higher than 150")
    @Positive(message = "Number must be positive")
    private Integer Armpress;

//    Best armpull for 3 reps
    @NotNull (message = "This field cannot be null")
    @Min(value = 20, message = "Number should not be less than 20")
    @Max(value = 150, message = "Number should not be higher than 150")
    @Positive(message = "Number must be positive")
    private Integer Armpull;

//    Best armpull for 15 reps
    @NotNull (message = "This field cannot be null")
    @Min(value = 20, message = "Number should not be less than 20")
    @Max(value = 150, message = "Number should not be higher than 150")
    @Positive(message = "Number must be positive")
    private Integer Armpullmax;




//    Schwinn

//    Athletes score
    @NotNull (message = "This field cannot be null")
    @Min(value = 1, message = "Number should not be less than 1")
    @Max(value = 100, message = "Number should not be higher than 100")
    @Positive(message = "Number must be positive")
    private Integer Score;

//    Coach observations can be tick box or text?
    @NotNull (message = "This field cannot be null")
    private String Observations;




//    Core and flexibility

//    The athletes basic flexibility score drop down box to choose from red amber green
    @NotNull (message = "This field cannot be null")
    private String Basicscore;

    @NotNull (message = "This field cannot be null")
    private String Notesonscore;

//    Choose from red amber of green
    @NotNull (message = "This field cannot be null")
    private String Flexibility;

    @NotNull (message = "This field cannot be null")
    private String Notesonflexibility;



//    Training History
    @NotNull (message = "This field cannot be null")
    private String Previoussports;

//    Number of months training last year
    @NotNull (message = "This field cannot be null")
    private String nummonthstraining;

//    Number of training session per week including competitions
    @NotNull (message = "This field cannot be null")
    private String numofsessionperweek;

//    Number of endurance sessions per week
    @NotNull (message = "This field cannot be null")
    private String numofendurancesessions;

//    Number of strength sessions per week
    @NotNull (message = "This field cannot be null")
    private String numofstrengthsessions;

//    Numbers of years training at this level
    @NotNull (message = "This field cannot be null")
    private String numofyearstraining;


//    Follow up

//    Should me multiple choice
    @NotNull (message = "This field cannot be null")
    private String NextStage;







}
