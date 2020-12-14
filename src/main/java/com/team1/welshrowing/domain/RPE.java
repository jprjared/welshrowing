package com.team1.welshrowing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RPE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    private Long RPEformId;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @NotNull(message = "This field cannot be null")
    private String dateofTest;

    @NotNull(message = "This field cannot be null")
    private String typeofSession;

    @NotNull(message = "This field cannot be null")
    private Integer rpe;

    @NotNull(message = "This field cannot be null")
    private Integer sessionDuration;
}
