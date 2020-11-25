to users (id, name, type, email, password) values (001, 'Emily Coach', 'coach', 'emily@coach.com', 'password' );
to users (id, name, type, email, password) values (002, 'Owain Coach', 'coach', 'owain@coach.com', 'password' );
to users (id, name, type, email, password) values (003, 'Rowan Boy', 'applicant', 'rowanb@generic.com', 'password' );
to users (id, name, type, email, password) values (004, 'Rowan Girl', 'applicant', 'rowang@generic.com', 'password' );
to users (id, name, type, email, password) values (005, 'Mandy Madeit', 'athlete', 'Mandy@rowing.com', 'password' );
to users (id, name, type, email, password) values (006, 'Freddie Failed', 'applicant', 'Freddie@generic.com', 'password' );

to applicants (user_id, user_name, height, age, application_situation) values (3,'Rowan Boy', 190, 18, 'pending');
to applicants (user_id, user_name, height, age, application_situation) values (4,'Rowan Girl', 180, 20, 'incomplete');
to applicants (user_id, user_name, height, age, application_situation) values (6,'Freddie Failed', 100, 15, 'unsuccessful');

to initial_test_results (testing_date, tested, comments, results, user_id, user_name) values ('12/12/2019', 'yes', 'good height, previous injury', 'successful', 3, 'Rowan Boy');
to initial_test_results (testing_date, tested, comments, results, user_id, user_name) values ('01/01/2020', 'no', '', 'pending', 4, 'Rowan Girl');
to initial_test_results (testing_date, tested, comments, results, user_id, user_name) values ('05/05/2020', 'yes', 'Not tall enough', 'unsuccessful', 6, 'Freddie Failed');