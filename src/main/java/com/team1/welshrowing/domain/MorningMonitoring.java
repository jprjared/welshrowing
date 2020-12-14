package com.team1.welshrowing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MorningMonitoring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    private Long morningMonitoringId;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @NotNull(message = "This field cannot be null")
    private Integer wakingHeartRate;

    @NotNull(message = "This field cannot be null")
    private Integer standingHeartRate;

    @NotNull(message = "This field cannot be null")
    @Min(0)
    @Max(10)
    private Integer perceivedShape;

    @NotNull(message = "This field cannot be null")
    @Min(0)
    @Max(10)
    private Integer perceivedMentalState;

    @NotNull(message = "This field cannot be null")
    private Double sleepQuantity;

    @NotNull(message = "This field cannot be null")
    @Min(0)
    @Max(10)
    private Integer sleepQuality;

}