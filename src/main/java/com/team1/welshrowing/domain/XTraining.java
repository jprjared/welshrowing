package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Past(message = "Date should only be past")
    @NotNull(message = "This field should not be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateOfTraining;

    @NotNull(message = "This field should not be null")
    private String typeOfTraining;

    @NotNull(message = "This field should not be null")
    private Integer totalTimeOfTraining;

    @NotNull(message = "This field should not be null")
    private Integer totalDistanceOfTraining;
}
