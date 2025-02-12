INSERT INTO user (id, name, email, password) VALUES (1, 'John Doe', 'john.doe@example.com', 'password123');
INSERT INTO user (id, name, email, password) VALUES (2, 'Jane Smith', 'jane.smith@example.com', 'password456');

INSERT INTO patient (id, name, age, gender, user_id) VALUES (1, 'Alice Johnson', 30, 'Female', 1);
INSERT INTO patient (id, name, age, gender, user_id) VALUES (2, 'Bob Brown', 45, 'Male', 1);
INSERT INTO patient (id, name, age, gender, user_id) VALUES (3, 'Charlie Davis', 50, 'Male', 2);

INSERT INTO heart_rate (id, patient_id, timestamp, heart_rate_value) VALUES (1, 1, '2023-10-01T10:00:00', 72);
INSERT INTO heart_rate (id, patient_id, timestamp, heart_rate_value) VALUES (2, 1, '2023-10-01T11:00:00', 75);
INSERT INTO heart_rate (id, patient_id, timestamp, heart_rate_value) VALUES (3, 2, '2023-10-01T10:30:00', 80);
INSERT INTO heart_rate (id, patient_id, timestamp, heart_rate_value) VALUES (4, 3, '2023-10-01T09:45:00', 68);