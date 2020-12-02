-- <<<<<<< HEAD
--
-- -- insert into users (id, name, type, email, password) values (001, 'Emily Coach', 'coach', 'emily@coach.com', 'password' );
-- -- insert into users (id, name, type, email, password) values (002, 'Owain Coach', 'coach', 'owain@coach.com', 'password' );
-- -- insert into users (id, name, type, email, password) values (003, 'Rowan Boy', 'applicant', 'rowanb@generic.com', 'password' );
-- -- insert into users (id, name, type, email, password) values (004, 'Rowan Girl', 'applicant', 'rowang@generic.com', 'password' );
-- -- insert into users (id, name, type, email, password) values (005, 'Mandy Madeit', 'athlete', 'Mandy@rowing.com', 'password' );
-- -- insert into users (id, name, type, email, password) values (006, 'Freddie Failed', 'applicant', 'Freddie@generic.com', 'password' );
-- --
-- -- insert into applicants (user_id, user_name, height, age, application_situation) values (3,'Rowan Boy', 190, 18, 'pending');
-- -- insert into applicants (user_id, user_name, height, age, application_situation) values (4,'Rowan Girl', 180, 20, 'incomplete');
-- -- insert into applicants (user_id, user_name, height, age, application_situation) values (6,'Freddie Failed', 100, 15, 'unsuccessful');
-- --
-- -- insert into initial_test_results (testing_date, tested, comments, results, user_id, user_name) values ('12/12/2019', 'yes', 'good height, previous injury', 'successful', 3, 'Rowan Boy');
-- -- insert into initial_test_results (testing_date, tested, comments, results, user_id, user_name) values ('01/01/2020', 'no', '', 'pending', 4, 'Rowan Girl');
-- -- insert into initial_test_results (testing_date, tested, comments, results, user_id, user_name) values ('05/05/2020', 'yes', 'Not tall enough', 'unsuccessful', 6, 'Freddie Failed');
-- INSERT INTO USER VALUES(1, 'test@test.com', TRUE, '$2a$10$gFGOvUUE/20tvXtwpL0v2OFupGbv4wwFEijCpOFZB9yts27QdIpFi', 'ATHLETE', 'test', null, null);
-- -- INSERT INTO USER VALUES(2, 'applicant@applicant.com', TRUE, 'password', '')
--
-- =======
INSERT INTO USER VALUES(1, 'test@test.com', TRUE, '$2a$10$gFGOvUUE/20tvXtwpL0v2OFupGbv4wwFEijCpOFZB9yts27QdIpFi', 'ATHLETE', 'test', null, null);
INSERT INTO USER VALUES(2, 'athlete@test.com', TRUE, '$2a$10$SrjqKixCIbBg1Yyo2Sc1I.lqM9LUixH8z9uXiTSUqis3qRB45tIF6', 'ATHLETE', 'athlete', null, null);
INSERT INTO USER VALUES(3, 'coach@test.com', TRUE, '$2a$10$SrjqKixCIbBg1Yyo2Sc1I.lqM9LUixH8z9uXiTSUqis3qRB45tIF6', 'COACH', 'coach', null, null);

INSERT INTO Applicant VALUES (4, 'rowanb@generic.com', 'Rowan Boy', 'Rowan', 'Smith', '07123456789', '2 Cardiff Road', 'CF24 5FG',
'Cardiff Uni', '2000-01-01', 190, 'Male', 'mum@gmail.com', 'yes', 'No', 'Facebook');
-- >>>>>>> master
