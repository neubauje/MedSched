-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

-- INSERT statements go here
INSERT INTO office (office_id, office_name, address, days_open, opening_hours, closing_hours, phone_number, cost_per_hour) VALUES ('0', 'Placeholder', 'I will be creating a new office', 'None yet', '00:00:00', '00:00:00', '0', 0);

insert into office (office_name, address, days_open, opening_hours, closing_hours, phone_number, cost_per_hour)
values ('Urgent Care', '5454 London ct Westerville  Ohio 43081', 'Monday through Friday', '09:30:00', '05:00:00', '6146079701', '250');

insert into office (office_name, address, days_open, opening_hours, closing_hours, phone_number, cost_per_hour)
values ('Eye Care', '5459 Paw street Libon Ohio 43210', 'Monday through Friday', '09:00:00', '05:00:00', '6146009501', '250');

insert into office (office_name, address, days_open, opening_hours, closing_hours, phone_number, cost_per_hour)
values ('Derma Care', '1 Paris street Sion Ohio 43222', 'Monday through Friday', '09:00:00', '05:00:00', '9146229500', '220');

insert into office (office_name, address, days_open, opening_hours, closing_hours, phone_number, cost_per_hour)
values ('Princeton-Plainsboro Teaching Hospital', '1 Plainsboro Road Plainsboro, NJ 08536', 'Monday through Friday', '09:00:00', '05:00:00', '6098537000', '250');

INSERT INTO app_user (user_name, password, role, salt) VALUES ('neubauje', 'ooBgnPs3zUWA7R8ucEuX/w==', 'Patient', '8VOktdHBnRuiIIEi5efm7I57Gn1c66IjVkun7uEeP2vcj+mDZnOj7kdQFLMDP5sX1oJGWUU04z1JWCH793zaB4JlImy1rrf+tebTLp+7WgNe7VhQiHnXIOeAHxupVpYylUpI83cvXAQCsyxK9T3PCHW/l6joaPwb1GxoC5YomP4=');
INSERT INTO app_user (user_name, password, role, salt) VALUES ('GHouse', 'p/gpTLc/oj8ODSHM563puQ==', 'Doctor', '1ovVsleIUh+hGhAue4YfVqP0RXJHpJxqjvJDX0Qn27+mucGcd0A3oOcVJ12c2jC/i9xZHjY9v+vZMSNnOySjcn4x6YPSBbwpmz7fE4UPTbLoXptaHG7pwGw23tuXhWfM06SEnF98aN41F8PoTbGy+Heti7aFoVyBquMsNE+0taY=');
INSERT INTO app_user (user_name, password, role, salt) VALUES ('autumnhound', '5iC/FORAJcQK/4TabOobCA==', 'Patient', 'OO6neIiuClAwZ8Y1m6ACzU27NbL7CbOR24lKAmbBUJk30/aFuwK+sGtK1qE5Eqj2gISLK0JLnVzK2haMPNkPP4ZZBrX8WsCTH4NqbdFFseKBfJaxC87xEBUNhMsK/AFmYd5D6FIABMrvvkYaHXfzIHaUEL7zDRzT2O/8mxnzktU=');
INSERT INTO app_user (user_name, password, role, salt) VALUES ('GFriedman', 'J5/WFN2WWc9aGDHJDQ2XpA==', 'Doctor', 'ySLOOHR+QS3oHCrywpcFMjc2EiO5hClnCaco6B5iwdJVrI62b7VAS5xk6MF5v9q551j7ObPhCViCKYjyIy+iLE5oWm5Qw49sr0jdH0zvfubA1R/DFWkbWidgT6/3qsDmTx0TLeehuUh49XBmP4beEMaAT6v7uKA/Rc3S48HCUN0=');
INSERT INTO app_user (user_name, password, role, salt) VALUES ('JWilson', 'e5TM6RaQQo4gYITCQ+jdJQ==', 'Doctor', 'pyazx3FJevwI7p/IM/csjrYVMxQaG1JVsoPERiGYYqtvnZhyIltVgKGwOX4mo5VUxWjGzWWBTBTPW2K0zCe5RdYdsJfe9+bDW0fyOdocthHtpUH98CKBbx2I5BkRtPrJ0Ymb8dHBfJGzvC9OwYJt/4XiPIzBakDewYHgBmWqf1Y=');
INSERT INTO app_user (user_name, password, role, salt) VALUES ('LCuddy', 'fGBoVMHMeIlPG+Ei8gnh3A==', 'Doctor', 'X5FIBv1t8OKfvS+1bKcy9MGl8Gs1F2DNWXr5BXa3TXZsPAvLXkKf2TeDbFRyj3qo4MKLWxc5REhh4Eu/0JpS1NpRAgcqGO9Z0e0z/16L2t7M6CZ5OCCfl/KMcY6ah1ouEfH56NTViNSMP6brMOOcq6E8IjsTk8CB1Ug6BYt6wjE=');
INSERT INTO app_user (user_name, password, role, salt) VALUES ('patient', '8VhVTQAllEOEQ0Pj4l/CXQ==', 'Patient', '5eATOj58aw0d8YyBB4GBXl+DjWCE2u3cEjqXqGcFvmCxdvHWmy4Eg7KsGlWqL+v6x1qXVe0J7RROQ4KMp4fGGH1MS6izoKSCAaHBjeFNONzsomcW7+w0zMzLSjE5uvT54Y++3CyMtXl3eoUk+71g2U6TfOvKP24qLnWbLqgrLBY=');
INSERT INTO app_user (user_name, password, role, salt) VALUES ('doctor', '2i/+dSO6Mm2R+DVNgdLnZA==', 'Doctor', 'GAFhgw+RuO0M7E9Z2V2ZY6peEC/X1K3TMPKj1i1zEYlBRDkI/33dh+xXQfVdqS0YZiTFkuKT7Fryunmzyc3nhZJvZa5houawgUdTpu3VhLxSMXmdGLyUWpPSqjOu9/XblC78eXKqAdU1FT+B8UEQIIjEeOwtzw643qHS9vR4+P8=');

INSERT INTO doctor (user_id, office_id, firstname, lastname, specialty) VALUES (2, 4, 'Gregory', 'House', 'Diagnostics');
INSERT INTO doctor (user_id, office_id, firstname, lastname, specialty) VALUES (4, 1, 'Gabriel', 'Friedman', 'Neurology');
INSERT INTO doctor (user_id, office_id, firstname, lastname, specialty) VALUES (5, 4, 'James', 'Wilson', 'Oncology');
INSERT INTO doctor (user_id, office_id, firstname, lastname, specialty) VALUES (6, 4, 'Lisa', 'Cuddy', 'Endocrinology');
INSERT INTO doctor (user_id, office_id, firstname, lastname, specialty) VALUES (8, 2, 'Demonstration', 'Doctor', 'Leading by example');

INSERT INTO patient (user_id, firstname, lastname, dob, phone, email, emergencyphone, emergencyname, insurance) VALUES (1, 'Jesse', 'Neubauer', '1990-06-29', 7404078022, 'neubauje@gmail.com', 4129742848, 'Heather Powers', 'Aetna');
INSERT INTO patient (user_id, firstname, lastname, dob, phone, email, emergencyphone, emergencyname, insurance) VALUES (3, 'Heather', 'Powers', '1982-10-13', 4129742848, 'autumnhound@gmail.com', 7404078022, 'Jesse Neubauer', 'Aetna');
INSERT INTO patient (user_id, firstname, lastname, dob, phone, email, emergencyphone, emergencyname, insurance) VALUES (7, 'Demonstration', 'Patient', '2000-01-01', 1112223333, 'patient@capstone.com', 2223334444, 'Me', 'Tech Elevator');
INSERT INTO patient (user_id, firstname, lastname, dob, phone, email, emergencyphone, emergencyname, insurance) VALUES (2, 'Francis', 'Nyamekye Jr', '1991-08-12', 1234567890, 'junior@fan.com', 9876543210, 'Freya', 'Aetna');
INSERT INTO patient (user_id, firstname, lastname, dob, phone, email, emergencyphone, emergencyname, insurance) VALUES (3, 'Phajindra', 'Bajgain', '1989-04-15', 9805431122, 'bajgain@gmail.com', 4506789401, 'Phajindra', 'Tech Elevator');
INSERT INTO patient (user_id, firstname, lastname, dob, phone, email, emergencyphone, emergencyname, insurance) VALUES (4, 'Ndeye', 'Sene', '1988-02-14', 3458904560, 'sene@hotmail.com', 6789803456, 'Matty', 'Aetna');
INSERT INTO patient (user_id, firstname, lastname, dob, phone, email, emergencyphone, emergencyname, insurance) VALUES (5, 'Randy', 'Casburn', '1983-06-02', 2760003333, 'randy@capstone.com', 2764448200, 'Randy', 'Tech Elevator');

INSERT INTO patient_doctor (patient_id, doctor_id) VALUES (1, 1);
INSERT INTO patient_doctor (patient_id, doctor_id) VALUES (1, 2);
INSERT INTO patient_doctor (patient_id, doctor_id) VALUES (2, 4);
INSERT INTO patient_doctor (patient_id, doctor_id) VALUES (2, 2);
INSERT INTO patient_doctor (patient_id, doctor_id) VALUES (2, 1);
INSERT INTO patient_doctor (patient_id, doctor_id) VALUES (3, 3);
INSERT INTO patient_doctor (patient_id, doctor_id) VALUES (3, 5);

INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('Physician was awesome', 5, '2022-04-09 18:44:03.371855', 1, 1, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('Physician was great', 4, '2022-04-09 18:44:03.371855', 3, 2, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('Physician was ok', 3, '2022-04-09 18:44:03.371855', 3, 3, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('Physician was not good', 7, '2022-04-09 18:44:03.371855', 4, 3, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('Physician was bad', 1, '2022-04-09 18:44:03.371855', 3, 4, 4, 'Apologies, you likely met with Dr. House.');
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('I went to this office.', 8, '2022-04-11 00:00:00.000000', 7, 2, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('The Employees were great to work with', 9, '2022-04-12 00:00:00.000000', 2, 1, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('Employees were disrespectful', 1, '2022-04-12 04:00:00.000000', 3, 4, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('I waited too long for someone to see me! This is unprofessional', 4, '2022-04-12 01:00:00.000000', 4, 3, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('This hospital has the best Doctors ever!', 10, '2022-04-13 10:00:00.000000', 1, 1, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('This hospital is soo expensive', 6, '2022-04-13 02:00:00.000000', 2, 1, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('Their service is top notch! I love the medical staff', 8, '2022-04-12 09:30:00.000000', 7, 2, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('The office was clean and well kept!', 9, '2022-04-13 11:15:00.000000', 3, 4, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('The employee are slow and rude', 1, '2022-04-12 04:45:00.000000', 4, 3, null, null);
INSERT INTO review (message, rating, date_submitted, user_id, office_id, doctor_id, doctor_response) VALUES ('Getting a time to see the doctor and way too hard! This has to change', 5, '2022-04-13 03:00:00.000000', 6, 2, null, null);

INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (1, '2022-04-11', '09:00:00', '10:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (1, '2022-04-22', '01:00:00', '02:00:00', true, 1, 'for regular checkup');
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (1, '2022-04-22', '11:00:00', '11:30:00', true, 1, 'eye checkup');
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (2, '2022-04-11', '02:00:00', '02:30:00', false, null, '');
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (2, '2022-04-09', '09:00:00', '09:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (2, '2022-04-10', '07:00:00', '09:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (1, '2022-04-11', '10:00:00', '11:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (2, '2022-04-25', '09:00:00', '10:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-16', '16:00:00', '17:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-17', '15:00:00', '16:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-18', '15:00:00', '16:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (1, '2022-04-12', '11:00:00', '12:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (1, '2022-04-14', '14:00:00', '15:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (1, '2022-04-15', '15:00:00', '16:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (1, '2022-04-13', '16:00:00', '17:00:00', true, 2, 'My everything hurts :(');
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (3, '2022-04-13', '13:00:00', '14:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (3, '2022-04-14', '13:00:00', '14:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (3, '2022-04-15', '15:00:00', '16:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (3, '2022-04-16', '15:00:00', '16:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (3, '2022-04-12', '11:00:00', '12:00:00', true, 3, 'This is a test! Let''s see if it generates a notification.');
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-22', '01:00:00', '01:30:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-12', '14:00:00', '15:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-13', '14:00:00', '15:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-15', '14:00:00', '15:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-17', '14:00:00', '15:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-18', '14:00:00', '15:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-19', '14:00:00', '15:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-20', '14:00:00', '15:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-21', '14:00:00', '15:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-14', '14:00:00', '15:00:00', true, 3, 'This is for demonstration purposes!');
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-12', '01:00:00', '02:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-12', '02:00:00', '03:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-12', '03:00:00', '04:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-12', '04:00:00', '05:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-13', '10:00:00', '11:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-13', '11:00:00', '12:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-13', '01:00:00', '02:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-13', '02:00:00', '03:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-12', '03:00:00', '04:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-12', '04:00:00', '05:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (3, '2022-04-15', '09:00:00', '10:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-15', '10:00:00', '11:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (3, '2022-04-15', '11:00:00', '12:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-15', '01:00:00', '02:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (1, '2022-04-15', '02:00:00', '03:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (2, '2022-04-15', '03:00:00', '04:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (4, '2022-04-15', '04:00:00', '05:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (3, '2022-04-18', '08:00:00', '09:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (2, '2022-04-18', '09:00:00', '10:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (1, '2022-04-18', '10:00:00', '11:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (4, '2022-04-18', '11:00:00', '12:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (3, '2022-04-18', '01:00:00', '02:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (1, '2022-04-18', '02:00:00', '03:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (2, '2022-04-18', '03:00:00', '04:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (4, '2022-04-18', '04:00:00', '05:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (1, '2022-04-19', '09:00:00', '10:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (2, '2022-04-19', '10:00:00', '11:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (3, '2022-04-19', '11:00:00', '12:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (4, '2022-04-19', '01:00:00', '02:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-19', '02:00:00', '03:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-19', '03:00:00', '04:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (3, '2022-04-19', '04:00:00', '05:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (2, '2022-04-20', '09:00:00', '10:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (3, '2022-04-20', '10:00:00', '11:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-20', '11:00:00', '12:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (4, '2022-04-20', '01:00:00', '02:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-20', '02:00:00', '03:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (5, '2022-04-20', '03:00:00', '04:00:00', false, null, null);
INSERT INTO time_slot (doctor_id, date, start_time, end_time, assigned, patient_id, description) VALUES (4, '2022-04-20', '04:00:00', '05:00:00', false, null, null);


INSERT INTO notification (patient_id, doctor_id, contents, date_time, acknowledged, intended_for_doctor) VALUES (2, 1, 'Heather Powers booked an appointment with you for 2022-04-13 at 16:00', '2022-04-11 11:13:14.724277', false, true);
INSERT INTO notification (patient_id, doctor_id, contents, date_time, acknowledged, intended_for_doctor) VALUES (3, 5, 'Demonstration Patient booked an appointment with you for 2022-04-12 at 11:00', '2022-04-11 11:25:31.609521', false, true);
INSERT INTO notification (doctor_id, patient_id, contents, date_time, acknowledged, intended_for_doctor) VALUES (5, 3, 'Demonstration Patient booked an appointment with you for 2022-04-14 at 14:00', '2022-04-11 14:53:14.164513', false, true);

COMMIT;

