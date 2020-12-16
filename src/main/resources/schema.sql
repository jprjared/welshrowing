CREATE TABLE IF NOT EXISTS `user` (
    `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `email` varchar(255) DEFAULT NULL,
    `is_active` bit(1) NOT NULL,
    `password` varchar(255) DEFAULT NULL,
    `roles` varchar(255) DEFAULT NULL,
    `user_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS `applicant` (
     `applicant_id` bigint(20) NOT NULL AUTO_INCREMENT,
     `address` varchar(255) DEFAULT NULL,
     `application_situation` varchar(255) DEFAULT NULL,
     `coach` varchar(255) DEFAULT NULL,
     `college_or_university_name` varchar(255) DEFAULT NULL,
     `comments` varchar(255) DEFAULT NULL,
     `dob` varchar(255) DEFAULT NULL,
     `first_name` varchar(255) DEFAULT NULL,
     `gender` varchar(255) DEFAULT NULL,
     `height` double DEFAULT NULL,
     `is_previously_tested` bit(1) DEFAULT NULL,
     `last_name` varchar(255) DEFAULT NULL,
     `parent_email` varchar(255) DEFAULT NULL,
     `passport_holder` varchar(255) DEFAULT NULL,
     `phone_number` varchar(255) DEFAULT NULL,
     `postcode` varchar(255) DEFAULT NULL,
     `where_did_hear` varchar(255) DEFAULT NULL,
     `user_id` bigint(20) DEFAULT NULL,
     PRIMARY KEY (`applicant_id`),
     CONSTRAINT `FKo175qdudgxor4i10p66j0faip` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);


CREATE TABLE IF NOT EXISTS `feedback` (
                            `feedback_id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `applicant_id` bigint(20) NOT NULL,
                            `file` varchar(255) DEFAULT NULL,
                            `message` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`feedback_id`)
);


CREATE TABLE IF NOT EXISTS `interview` (
                             `interview_id` bigint(20) NOT NULL,
                             `aspirations` varchar(255) DEFAULT NULL,
                             `coach_role` varchar(255) DEFAULT NULL,
                             `intrinsicorextrinsic` int(11) DEFAULT NULL,
                             `intrinsicorextrinsiccomp` int(11) DEFAULT NULL,
                             `opportunitymeaning` varchar(255) DEFAULT NULL,
                             `possiblebarriers` varchar(255) DEFAULT NULL,
                             `processoroutcome` int(11) DEFAULT NULL,
                             `processoroutcomecomp` int(11) DEFAULT NULL,
                             `rolemodels` varchar(255) DEFAULT NULL,
                             `sportexp` varchar(255) DEFAULT NULL,
                             `whatcanyoubring` varchar(255) DEFAULT NULL,
                             `yourstory` varchar(255) DEFAULT NULL,
                             `applicant_id` bigint(20) DEFAULT NULL,
                             `date_time` datetime(6) DEFAULT NULL,
                             `elitesportdeff` varchar(255) DEFAULT NULL,
                             `user_id` bigint(20) DEFAULT NULL,
                             PRIMARY KEY (`interview_id`),
                             CONSTRAINT `FKr1uf9tvt2l7jx0x7riec5hfxe` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);



CREATE TABLE IF NOT EXISTS `morning_monitoring` (
                                      `morning_monitoring_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                      `date_time` datetime(6) DEFAULT NULL,
                                      `perceived_mental_state` int(11) DEFAULT NULL,
                                      `perceived_shape` int(11) DEFAULT NULL,
                                      `sleep_quality` int(11) DEFAULT NULL,
                                      `sleep_quantity` double DEFAULT NULL,
                                      `standing_heart_rate` int(11) DEFAULT NULL,
                                      `waking_heart_rate` int(11) DEFAULT NULL,
                                      `osmotic_heart_rate` int(11) DEFAULT NULL,
                                      `user_id` bigint(20) DEFAULT NULL,
                                      PRIMARY KEY (`morning_monitoring_id`),
                                      CONSTRAINT `FKjv0wycdewcilctlyxm1q9vnai` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE IF NOT EXISTS `personality_interview` (
                                         `interview_id` int(11) NOT NULL,
                                         `abilitytoworkwithothers` int(11) DEFAULT NULL,
                                         `bravery` int(11) DEFAULT NULL,
                                         `competitiveness` int(11) DEFAULT NULL,
                                         `confidence` int(11) DEFAULT NULL,
                                         `hardworking` int(11) DEFAULT NULL,
                                         `lightheartedness` int(11) DEFAULT NULL,
                                         `onoffswitch` int(11) DEFAULT NULL,
                                         `open_mindedness` int(11) DEFAULT NULL,
                                         `resilience` int(11) DEFAULT NULL,
                                         `risktaking` int(11) DEFAULT NULL,
                                         `selfdiscipline` int(11) DEFAULT NULL,
                                         `applicant_id` bigint(20) DEFAULT NULL,
                                         `date_time` datetime(6) DEFAULT NULL,
                                         `user_id` bigint(20) DEFAULT NULL,
                                         PRIMARY KEY (`interview_id`),
                                         CONSTRAINT `FKrx9kfjnou8lwgo4wh6ry5tdhr` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);



CREATE TABLE IF NOT EXISTS `physical_test` (
                                 `test_id` int(11) NOT NULL,
                                 `armpress` int(11) DEFAULT NULL,
                                 `armpull` int(11) DEFAULT NULL,
                                 `armpullmax` int(11) DEFAULT NULL,
                                 `armspan` int(11) DEFAULT NULL,
                                 `basicscore` varchar(255) DEFAULT NULL,
                                 `coachtesting` varchar(255) DEFAULT NULL,
                                 `contactphone` varchar(255) DEFAULT NULL,
                                 `dateoftest` varchar(255) DEFAULT NULL,
                                 `emailofguardian` varchar(255) DEFAULT NULL,
                                 `flexibility` varchar(255) DEFAULT NULL,
                                 `homephonenumber` varchar(255) DEFAULT NULL,
                                 `injuries` varchar(255) DEFAULT NULL,
                                 `legpress` int(11) DEFAULT NULL,
                                 `nameofathlete` varchar(255) DEFAULT NULL,
                                 `nameofparent` varchar(255) DEFAULT NULL,
                                 `next_stage` varchar(255) DEFAULT NULL,
                                 `notesonflexibility` varchar(255) DEFAULT NULL,
                                 `notesonscore` varchar(255) DEFAULT NULL,
                                 `observations` varchar(255) DEFAULT NULL,
                                 `previoussports` varchar(255) DEFAULT NULL,
                                 `relationshiptoathlete` varchar(255) DEFAULT NULL,
                                 `schoolorcluboruniversity` varchar(255) DEFAULT NULL,
                                 `score` int(11) DEFAULT NULL,
                                 `standingheight` int(11) DEFAULT NULL,
                                 `weight` int(11) DEFAULT NULL,
                                 `applicant_id` bigint(20) DEFAULT NULL,
                                 `date_time` datetime(6) DEFAULT NULL,
                                 `nummonthstraining` varchar(255) DEFAULT NULL,
                                 `numofendurancesessions` varchar(255) DEFAULT NULL,
                                 `numofsessionperweek` varchar(255) DEFAULT NULL,
                                 `numofstrengthsessions` varchar(255) DEFAULT NULL,
                                 `numofyearstraining` varchar(255) DEFAULT NULL,
                                 `user_id` bigint(20) DEFAULT NULL,
                                 PRIMARY KEY (`test_id`),
                                 CONSTRAINT `FKco6y0isk8jvcxymh2lys9fama` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);


CREATE TABLE IF NOT EXISTS `rpe` (
                       `rpeform_id` bigint(20) NOT NULL AUTO_INCREMENT,
                       `date_time` datetime(6) DEFAULT NULL,
                       `dateof_test` varchar(255) DEFAULT NULL,
                       `rpe` int(11) DEFAULT NULL,
                       `session_duration` int(11) DEFAULT NULL,
                       `typeof_session` varchar(255) DEFAULT NULL,
                       `user_id` bigint(20) DEFAULT NULL,
                       PRIMARY KEY (`rpeform_id`),
                       CONSTRAINT `FKhanscaigddo3x05px98k523wp` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);


CREATE TABLE IF NOT EXISTS `xtraining` (
                             `x_training_id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `date_of_training` date DEFAULT NULL,
                             `date_time` datetime(6) DEFAULT NULL,
                             `total_distance_of_training` int(11) DEFAULT NULL,
                             `total_time_of_training` int(11) DEFAULT NULL,
                             `type_of_training` varchar(255) DEFAULT NULL,
                             `user_id` bigint(20) DEFAULT NULL,
                             PRIMARY KEY (`x_training_id`),
                             CONSTRAINT `FKdb74vuuxar81ex6o6sksjmqdc` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);