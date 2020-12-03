package com.team1.welshrowing.web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class InterviewForm {
    private Long applicantId;

    private String Name;

//    What to you is the difference between taking part and competing in sport?What does High Performance/Elite Sport mean?
    private String elitesportdeff;

//    What is your experience and feelings about sport to date?
    private String Sportexp;

//    What are your aspirations? (in rowing and outside rowing)
    private String Aspirations;

//    What can you bring to this high-performance training environment?
    private String Whatcanyoubring;


//    What is the role of the coaches to you?
    private String CoachRole;

//    What, if any, are potential barriers to your compliance and success on a programme like this?
    private String Possiblebarriers;

//    What motivates you when training?
    private Integer Processoroutcome;

//        What motivates you when training?
    private Integer Intrinsicorextrinsic;

//    What motivates you when competing?
    private Integer Processoroutcomecomp;

//    What motivates you when competing?
    private Integer Intrinsicorextrinsiccomp;

//    What does this opportunity mean to you?
    private String Opportunitymeaning;

//    Do you have any role models? Why them?
    private String Rolemodels;

//    Your story
    private String Yourstory;




}
