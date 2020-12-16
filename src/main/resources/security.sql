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

-- SECURE STORAGE OF SENSITIVE DATA IN DB

-- Encrypt Email Before Insert Trigger
DROP TRIGGER IF EXISTS encryptEmail;
DELIMITER //
CREATE TRIGGER encryptEmail
BEFORE INSERT ON USER
FOR EACH ROW BEGIN
	IF EXISTS (SELECT NEW.email) THEN
		SET NEW.email = AES_ENCRYPT(NEW.email,'J9DVC?n(');
    END IF;
END//
  DELIMITER ;

-- Encrypt XTraining Before Insert Trigger
DROP TRIGGER IF EXISTS encryptXTraining;
DELIMITER //
CREATE TRIGGER encryptXTraining
BEFORE INSERT ON XTRAINING
FOR EACH ROW BEGIN
	IF EXISTS (SELECT NEW.type_of_training) THEN
		SET NEW.type_of_training = AES_ENCRYPT(NEW.type_of_training,'J9DVC?n(');
    END IF;
END//
  DELIMITER ;

-- Encrypt RPE Before Insert Trigger
DROP TRIGGER IF EXISTS encryptRPE;
DELIMITER //
CREATE TRIGGER encryptRPE
BEFORE INSERT ON RPE
FOR EACH ROW BEGIN
	IF EXISTS (SELECT NEW.typeof_session) THEN
		SET NEW.typeof_session = AES_ENCRYPT(NEW.typeof_session,'J9DVC?n(');
    END IF;
END//
  DELIMITER ;

-- Encrypt Feedback Before Insert Trigger
DROP TRIGGER IF EXISTS encryptFeedback;
DELIMITER //
CREATE TRIGGER encryptFeedback
BEFORE INSERT ON feedback
FOR EACH ROW BEGIN
	IF EXISTS (SELECT NEW.message) THEN
		SET NEW.message = AES_ENCRYPT(NEW.message,'J9DVC?n(');
    END IF;
END//
  DELIMITER ;

-- Encrypt Applicant Before Insert Trigger
DROP TRIGGER IF EXISTS encryptApplicantBeforeInsert;
DELIMITER //
CREATE TRIGGER encryptApplicantBeforeInsert
BEFORE INSERT ON applicant
FOR EACH ROW BEGIN
	IF EXISTS (SELECT NEW.first_name) THEN
		SET NEW.first_name = AES_ENCRYPT(NEW.first_name,'J9DVC?n(');
    END IF;
    IF EXISTS (SELECT NEW.last_name) THEN
		SET NEW.last_name = AES_ENCRYPT(NEW.last_name,'J9DVC?n(');
    END IF;
    IF EXISTS (SELECT NEW.dob) THEN
		SET NEW.dob = AES_ENCRYPT(NEW.dob,'J9DVC?n(');
    END IF;
	IF EXISTS (SELECT NEW.address) THEN
		SET NEW.address = AES_ENCRYPT(NEW.address,'J9DVC?n(');
    END IF;
    IF EXISTS (SELECT NEW.postcode) THEN
		SET NEW.postcode = AES_ENCRYPT(NEW.postcode,'J9DVC?n(');
    END IF;
    IF EXISTS (SELECT NEW.college_or_university_name) THEN
		SET NEW.college_or_university_name = AES_ENCRYPT(NEW.college_or_university_name,'J9DVC?n(');
    END IF;
    IF EXISTS (SELECT NEW.phone_number) THEN
		SET NEW.phone_number = AES_ENCRYPT(NEW.phone_number,'J9DVC?n(');
    END IF;
    IF EXISTS (SELECT NEW.comments) THEN
		SET NEW.comments = AES_ENCRYPT(NEW.comments,'J9DVC?n(');
    END IF;
    IF EXISTS (SELECT NEW.parent_email) THEN
		SET NEW.parent_email = AES_ENCRYPT(NEW.parent_email,'J9DVC?n(');
    END IF;
    IF EXISTS (SELECT NEW.passport_holder) THEN
		SET NEW.passport_holder = AES_ENCRYPT(NEW.passport_holder,'J9DVC?n(');
    END IF;
END//
  DELIMITER ;

-- Encrypt Address After Update Trigger
DROP TRIGGER IF EXISTS encryptApplicantAfterUpdate;
DELIMITER //
CREATE TRIGGER encryptApplicantAfterUpdate
AFTER UPDATE ON applicant
FOR EACH ROW BEGIN
	IF NEW.first_name <> OLD.first_name THEN
		INSERT INTO applicant (first_name) VALUES((AES_ENCRYPT(first_name,'J9DVC?n(')));
    END IF;
    IF NEW.last_name <> OLD.last_name THEN
		INSERT INTO applicant (last_name) VALUES((AES_ENCRYPT(last_name,'J9DVC?n(')));
    END IF;
    IF NEW.dob <> OLD.dob THEN
		INSERT INTO applicant (dob) VALUES((AES_ENCRYPT(dob,'J9DVC?n(')));
    END IF;
	IF NEW.address <> OLD.address THEN
		INSERT INTO applicant (address) VALUES((AES_ENCRYPT(address,'J9DVC?n(')));
    END IF;
	IF NEW.postcode <> OLD.postcode THEN
		INSERT INTO applicant (postcode) VALUES((AES_ENCRYPT(postcode,'J9DVC?n(')));
    END IF;
    IF NEW.college_or_university_name <> OLD.college_or_university_name THEN
		INSERT INTO applicant (college_or_university_name) VALUES((AES_ENCRYPT(college_or_university_name,'J9DVC?n(')));
    END IF;
    IF NEW.phone_number <> OLD.phone_number THEN
		INSERT INTO applicant (phone_number) VALUES((AES_ENCRYPT(phone_number,'J9DVC?n(')));
    END IF;
    IF NEW.comments <> OLD.comments THEN
		INSERT INTO applicant (comments) VALUES((AES_ENCRYPT(comments,'J9DVC?n(')));
    END IF;
    IF NEW.parent_email <> OLD.parent_email THEN
		INSERT INTO applicant (parent_email) VALUES((AES_ENCRYPT(parent_email,'J9DVC?n(')));
    END IF;
    IF NEW.passport_holder <> OLD.passport_holder THEN
		INSERT INTO applicant (passport_holder) VALUES((AES_ENCRYPT(passport_holder,'J9DVC?n(')));
    END IF;
END//
  DELIMITER ;