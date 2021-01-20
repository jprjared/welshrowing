package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long athleteId;

    private String firstName;

    private String lastName;

    private Date startDate;

    private String formsSigned;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId")
    private User user;
}
