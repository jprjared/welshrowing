package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * Represents a User.
 */
public class User {


    /**
     * A unique suffix that will identify the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * The user name of the user.
     */
    @NotBlank(message = "Username cannot be empty")
    private String userName;

    /**
     * The email of the user.
     */
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email")
    private String email;

    /**
     * The password of the user.
     */
    @NotBlank(message = "Password cannot be empty")
    private String password;

    /**
     * Is this user active.
     */
    @AssertTrue
    private boolean isActive = true;

    /**
     * The roles of this user.
     */
    private String roles;

}
