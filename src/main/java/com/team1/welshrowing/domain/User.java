package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

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
    @ColumnTransformer(read = "cast(aes_decrypt(email, 'J9DVC?n(') as char(255))")
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

}
