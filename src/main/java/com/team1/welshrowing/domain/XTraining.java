package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class XTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    private Long xTrainingId;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    private Date dateOfTraining;

    private String typeOfTraining;

    private Integer totalTimeOfTraining;

    private Integer totalDistanceOfTraining;
}
