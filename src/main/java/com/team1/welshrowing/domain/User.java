package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * Represents a User.
 */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * A unique suffix that will identify the user.
     */
    private Long user_id;

    /**
     * The user name of the user.
     */
    private String user_name;

    /**
     * The type of the user. It can be coach/athlete.
     */
    private String user_type;

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * Linking User Object with Athlete Object
     * Many to One Relationship
     * Joined with user_id = athlete_id.
     */
    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private Athlete athlete_id ;

    /**
     * Linking User Object and Applicant Object
     * Many to One Relationship
     * Joined with user_id = applicant_id.
     */
    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant_id ;

}
