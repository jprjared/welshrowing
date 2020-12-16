INSERT INTO USER (email, is_active, password, roles, user_name) VALUES('daviesdr9@cardiff.ac.uk', TRUE, '$2a$10$gFGOvUUE/20tvXtwpL0v2OFupGbv4wwFEijCpOFZB9yts27QdIpFi', 'ATHLETE', 'test');
INSERT INTO USER (email, is_active, password, roles, user_name) VALUES('daviesdr9@cardiff.ac.uk', TRUE, '$2a$10$SrjqKixCIbBg1Yyo2Sc1I.lqM9LUixH8z9uXiTSUqis3qRB45tIF6', 'ATHLETE', 'athlete');
INSERT INTO USER (email, is_active, password, roles, user_name) VALUES('daviesdr9@cardiff.ac.uk', TRUE, '$2a$10$SrjqKixCIbBg1Yyo2Sc1I.lqM9LUixH8z9uXiTSUqis3qRB45tIF6', 'ATHLETE', 'passedathlete');
INSERT INTO USER (email, is_active, password, roles, user_name) VALUES('coach@test.com', TRUE, '$2a$10$SrjqKixCIbBg1Yyo2Sc1I.lqM9LUixH8z9uXiTSUqis3qRB45tIF6', 'COACH', 'coach');
INSERT INTO APPLICANT (address, application_situation, coach, college_or_university_name, comments, dob, first_name, gender, height, is_previously_tested, last_name, parent_email, passport_holder, phone_number, postcode, where_did_hear, user_id) VALUES('Cardiff', 'Pending', 'Emily', 'Cardiff University', 'Not trying enough', '15/04/1996', 'Ryan', 'MALE', 180.0, FALSE, 'Davies', 'parent@example.com', 1, '07111111111', 'CF24', 'Friends', 1);
INSERT INTO APPLICANT (address, application_situation, coach, college_or_university_name, comments, dob, first_name, gender, height, is_previously_tested, last_name, parent_email, passport_holder, phone_number, postcode, where_did_hear, user_id) VALUES('Cardiff', 'Passed', 'Emily', 'Swansea University', 'Very Positive', '12/06/1998', 'John', 'MALE', 190.0, FALSE, 'Smith', 'parent@example.com', 1, '07111111111', 'CF10', 'Internet', 2);
INSERT INTO APPLICANT (address, application_situation, coach, college_or_university_name, comments, dob, first_name, gender, height, is_previously_tested, last_name, parent_email, passport_holder, phone_number, postcode, where_did_hear, user_id) VALUES('Cardiff', 'Accepted', 'Emily', 'Cardiff University', 'Very Positive', '20/01/2000', 'Doctor', 'FEMALE', 170.0, FALSE, 'Who', 'parent@example.com', 1, '07111111111', 'CF20', 'Facebook', 3);
INSERT INTO FEEDBACK (applicant_id, file, message) VALUES(1, 'MESSAFEEAD','file.txt');
INSERT INTO morning_monitoring (date_time, perceived_mental_state, perceived_shape, sleep_quality, sleep_quantity, standing_heart_rate, waking_heart_rate, osmotic_heart_rate, user_id) VALUES('2020-12-11 12:00:00', 10, 5, 6, 10, 9, 70, 60, 2);
INSERT INTO morning_monitoring (date_time, perceived_mental_state, perceived_shape, sleep_quality, sleep_quantity, standing_heart_rate, waking_heart_rate, osmotic_heart_rate, user_id) VALUES('2020-12-10 12:00:00', 20, 7, 7, 10, 9, 30, 50, 2);
INSERT INTO morning_monitoring (date_time, perceived_mental_state, perceived_shape, sleep_quality, sleep_quantity, standing_heart_rate, waking_heart_rate, osmotic_heart_rate, user_id) VALUES('2020-12-9 12:00:00', 15, 8, 7, 9, 9, 35, 50, 2);
INSERT INTO morning_monitoring (date_time, perceived_mental_state, perceived_shape, sleep_quality, sleep_quantity, standing_heart_rate, waking_heart_rate, osmotic_heart_rate, user_id) VALUES('2020-12-8 12:00:00', 6, 9, 8, 9, 9, 37, 43, 2);
INSERT INTO morning_monitoring (date_time, perceived_mental_state, perceived_shape, sleep_quality, sleep_quantity, standing_heart_rate, waking_heart_rate, osmotic_heart_rate, user_id) VALUES('2020-12-7 12:00:00', 9, 7, 8, 8, 9, 40, 49, 2);
INSERT INTO morning_monitoring (date_time, perceived_mental_state, perceived_shape, sleep_quality, sleep_quantity, standing_heart_rate, waking_heart_rate, osmotic_heart_rate, user_id) VALUES('2020-12-6 12:00:00', 7, 10, 4, 9, 9, 41, 48, 2);
INSERT INTO xtraining (date_of_training, date_time, total_distance_of_training, total_time_of_training, type_of_training, user_id) VALUES('2020-12-6', '2020-12-6 12:00:00', 10, 100, 'Outdoor Run', 3);
INSERT INTO xtraining (date_of_training, date_time, total_distance_of_training, total_time_of_training, type_of_training, user_id) VALUES('2020-12-10', '2020-12-10 12:00:00', 20, 200, 'Road Cycle', 3);
INSERT INTO xtraining (date_of_training, date_time, total_distance_of_training, total_time_of_training, type_of_training, user_id) VALUES('2020-12-11', '2020-12-11 12:00:00', 30, 300, 'Concept II / Watt Bike', 3);
INSERT INTO xtraining (date_of_training, date_time, total_distance_of_training, total_time_of_training, type_of_training, user_id) VALUES('2020-12-12', '2020-12-12 12:00:00', 40, 400, 'Swimming', 3);
INSERT INTO rpe (date_time, dateof_test, rpe, session_duration, typeof_session, user_id) VALUES ('2020-12-14 19:32:40.957000', '2020-12-03', 5, 100, 'Water', 2);
