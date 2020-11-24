package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String user_name;

    private String user_type;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private Athlete athlete_id ;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant_id ;

}
