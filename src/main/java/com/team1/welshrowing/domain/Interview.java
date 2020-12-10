package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OrderBy
    private Long InterviewId;

    private Long applicantId;

    @NotNull(message = "This field cannot be null")
    private String elitesportdeff;

    @NotNull(message = "This field cannot be null")
    private String Sportexp;

    @NotNull(message = "This field cannot be null")
    private String Aspirations;

    @NotNull(message = "This field cannot be null")
    private String Whatcanyoubring;

    @NotNull(message = "This field cannot be null")
    private String CoachRole;

    @NotNull(message = "This field cannot be null")
    private String Possiblebarriers;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 10, message = "Number should not be higher than 10")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Processoroutcome;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 10, message = "Number should not be higher than 10")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Intrinsicorextrinsic;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 10, message = "Number should not be higher than 10")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Processoroutcomecomp;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 10, message = "Number should not be higher than 10")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Intrinsicorextrinsiccomp;

    @NotNull(message = "This field cannot be null")
    private String Opportunitymeaning;

    @NotNull(message = "This field cannot be null")
    private String Rolemodels;

    @NotNull(message = "This field cannot be null")
    private String Yourstory;
}
