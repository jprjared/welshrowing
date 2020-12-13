package com.team1.welshrowing.web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class RPEForm {

    private Date dateTime;

    private Date dateofTest;

    private String typeofSession;

    private Integer rpe;

    private Integer sessionDuration;




}
