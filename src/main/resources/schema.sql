SET MODE MySQL;
SET IGNORECASE = TRUE;

CREATE SCHEMA IF NOT EXISTS welsh_rowing_db;

SET SCHEMA welsh_rowing_db;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `applicants`;
DROP TABLE IF EXISTS `initial_test_results`;
-- DROP TABLE IF EXISTS ``;
-- DROP TABLE IF EXISTS ``;
-- DROP TABLE IF EXISTS ``;
-- DROP TABLE IF EXISTS ``;

CREATE TABLE IF NOT EXISTS `users` (
    `id`INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `type` VARCHAR(200) NOT NULL,
    `email` VARCHAR(200) NOT NULL,
    `password` VARCHAR(200) NOT NULL,

    PRIMARY KEY (`id`)
)

    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `applicants` (
    `user_id` INT UNSIGNED NOT NULL,
    `user_name` VARCHAR(200),
    `height` INT,
    `age` INT,
    `application_situation` VARCHAR,
--     `coach` VARCHAR,

    FOREIGN KEY (`user_id`) REFERENCES users(`id`),
    FOREIGN KEY (`user_name`) REFERENCES users(`name`)
)

    ENGINE =InnoDB;

CREATE TABLE IF NOT EXISTS `initial_test_results` (
--     `id` INT,
    `testing_date` DATE,
    `tested` VARCHAR(200),
    `comments` VARCHAR(500),
    `results` VARCHAR(200),
    `user_id` INT UNSIGNED NOT NULL,
    `user_name` VARCHAR(200),
--     `coach` VARCHAR,

    FOREIGN KEY (`user_id`) REFERENCES users(`id`),
    FOREIGN KEY (`user_name`) REFERENCES users(`name`),
)
    ENGINE = InnoDB;
