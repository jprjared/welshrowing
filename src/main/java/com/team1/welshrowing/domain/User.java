package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @NotNull(message = "Username cannot be empty")
    private String userName;

    /**
     * The email of the user.
     */
    @NotNull(message = "Email cannot be empty")
    @Email
    @Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Please enter a valid email")
    //Adopted pattern from https://www.w3schools.com/tags/att_input_pattern.asp [Accessed: 9 December 2020]
    private String email;

    /**
     * The password of the user.
     */
    @NotNull(message = "Password cannot be empty")
//    @Pattern(regexp = "[a-zA-Z\\d]{8,}", message="Must be at least 8 alphanumeric characters")
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
