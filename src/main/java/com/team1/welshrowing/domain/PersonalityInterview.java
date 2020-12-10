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

public class PersonalityInterview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OrderBy
    private Integer InterviewId;

    private Long applicantId;

    //    private Long userId;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 100, message = "Number should not be higher than 100")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Risktaking;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 100, message = "Number should not be higher than 100")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Bravery;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 100, message = "Number should not be higher than 100")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Resilience;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 100, message = "Number should not be higher than 100")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Hardworking;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 100, message = "Number should not be higher than 100")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Confidence;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 100, message = "Number should not be higher than 100")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer OpenMindedness;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 100, message = "Number should not be higher than 100")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Onoffswitch;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 100, message = "Number should not be higher than 100")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Lightheartedness;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 100, message = "Number should not be higher than 100")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Selfdiscipline;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 100, message = "Number should not be higher than 100")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Abilitytoworkwithothers;

    @NotNull(message = "This field cannot be null")
    @Min(value = 0, message = "Number should not be less than 0")
    @Max(value = 100, message = "Number should not be higher than 100")
    @PositiveOrZero(message = "Number must be positive or zero")
    private Integer Competitiveness;
}
