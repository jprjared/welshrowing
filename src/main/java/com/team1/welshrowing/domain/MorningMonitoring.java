package com.team1.welshrowing.domain;

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
import javax.validation.constraints.Size;
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
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    private Integer wakingHeartRate;

    private Integer standingHeartRate;

    @Size(min = 1, max = 10, message = "Perceived shape must be between 1 and 10")
    private Integer perceivedShape;

    @Size(min = 1, max = 10, message = "Perceived mental state must be between 1 and 10")
    private Integer perceivedMentalState;

    private Double sleepQuantity;

    @Size(min = 1, max = 10, message = "Sleep quality must be between 1 and 10")
    private Integer sleepQuality;

}