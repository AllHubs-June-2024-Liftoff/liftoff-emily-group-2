--Create Database
CREATE DATABASE parkd_user_management;

--After creating Database, USE to select database
USE parkd_user_management;


--Create Admin user
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'P@ssword123';
GRANT ALL PRIVILEGES ON parkd_user_management.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;


--Create table for capturing created/registered users data
CREATE TABLE registered_users (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL, 
    pw_hash VARCHAR(60) NOT NULL         
);

--For injecting staged data into registered_users table
INSERT INTO registered_users (username, pw_hash) VALUES
('user1', 'hashed_password_1'),
('user2', 'hashed_password_2'),
('user3', 'hashed_password_3');
etc...

--To verify new staged data
SELECT * FROM registered_users;