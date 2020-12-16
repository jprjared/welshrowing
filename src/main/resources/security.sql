DROP FUNCTION IF EXISTS strip_tags;

-- Function adapted from code example at https://forums.mysql.com/read.php?52,177343,177985 [Accessed: 15 December 2020]
-- This function strips HTML tags from a given input
DELIMITER ;;
CREATE FUNCTION strip_tags(dirty varchar(4000))
    RETURNS varchar(4000)
    DETERMINISTIC
BEGIN
    DECLARE start, end, length int;
    WHILE LOCATE('<', dirty) > 0 AND LOCATE('>', dirty, LOCATE('<', dirty)) > 0 DO
            BEGIN
                SET start = LOCATE('<', dirty), end = LOCATE('>', dirty, LOCATE('<', dirty));
                SET length = ( end - start) + 1;
                IF length > 0 THEN
                    BEGIN
                        SET dirty = INSERT(dirty, start, length, '');
                    END;
                END IF;
            END;
        END WHILE;
    RETURN dirty;
END;;
DELIMITER ;

-- Ensures that user roles can only be either athlete or coach, and not both
ALTER TABLE user ADD CONSTRAINT IF NOT EXISTS valid_role CHECK (roles='ATHLETE' OR roles='COACH');

-- Ensures that the password is hashed using bcrypt
-- Regex pattern adapted from https://stackoverflow.com/questions/31417387/regular-expression-to-find-bcrypt-hash [Accessed: 15 December 2020]
ALTER TABLE user ADD CONSTRAINT IF NOT EXISTS is_bcrypt CHECK (password REGEXP '^\\$2[ayb]\\$.{56}$');

-- Ensures that the email field follows a valid email format
-- Adapted from code examples at https://stackoverflow.com/questions/19154453/how-to-write-a-query-to-ensure-email-contains [Accessed: 15 December 2020]
ALTER TABLE user ADD CONSTRAINT IF NOT EXISTS check_email CHECK (email LIKE '%_@__%.__%');

CREATE USER IF NOT EXISTS 'athlete'@'localhost' identified BY 'welshrowing';
GRANT UPDATE ON welshrowing.* TO 'athlete'@'localhost';
GRANT SELECT ON welshrowing.* TO 'athlete'@'localhost';
GRANT INSERT ON welshrowing.* TO 'athlete'@'localhost';
GRANT CREATE ON welshrowing.* TO 'athlete'@'localhost';
GRANT REFERENCES ON welshrowing.* TO 'athlete'@'localhost';