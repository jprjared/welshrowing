package com.team1.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long feedbackId;

        private long applicantId;

        @ColumnTransformer(read = "cast(aes_decrypt(feedback, 'J9DVC?n(') as char(255))")
        private String message;

        private String file;
}

