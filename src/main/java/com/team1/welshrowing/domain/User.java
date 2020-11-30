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
    private Long userId;

    /**
     * The user name of the user.
     */
    private String userName;

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * Is this user active.
     */
    private boolean isActive = true;

    /**
     * The roles of this user.
     */
    private String roles;

    /**
     * Linking User Object with Athlete Object
     * Many to One Relationship
     * Joined with user_id = athlete_id.
     */
    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private Athlete athleteId;

    /**
     * Linking User Object and Applicant Object
     * Many to One Relationship
     * Joined with user_id = applicant_id.
     */
    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicantId;

}
