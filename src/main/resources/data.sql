INSERT INTO USER VALUES(1, 'test@test.com', TRUE, '$2a$10$gFGOvUUE/20tvXtwpL0v2OFupGbv4wwFEijCpOFZB9yts27QdIpFi', 'ATHLETE', 'test');
INSERT INTO USER VALUES(2, 'athlete@test.com', TRUE, '$2a$10$SrjqKixCIbBg1Yyo2Sc1I.lqM9LUixH8z9uXiTSUqis3qRB45tIF6', 'ATHLETE', 'athlete');
INSERT INTO USER VALUES(3, 'coach@test.com', TRUE, '$2a$10$SrjqKixCIbBg1Yyo2Sc1I.lqM9LUixH8z9uXiTSUqis3qRB45tIF6', 'COACH', 'coach');
INSERT INTO APPLICANT VALUES(1, 'Cardiff', 'Pending', 'Emily', 'Cardiff University', '15/04/1996', 'Ryan', 'MALE', 24.0, FALSE, 'Davies', 'parent@example.com', '242424', '07111111111', 'CF24', 'Friends', 1);
INSERT INTO APPLICANT VALUES(2, 'Cardiff', 'Accepted', 'Emily', 'Cardiff University', '15/04/1996', 'John', 'MALE', 24.0, FALSE, 'Smith', 'parent@example.com', '242424', '07111111111', 'CF24', 'Friends', 2);
INSERT INTO FEEDBACK VALUES(1, 1, 'MESSAFEEAD','file.txt');
INSERT INTO morning_monitoring VALUES(1, '2020-12-11 13:18:05.473', 5, 6, 7, 9, 50, 70, 2);