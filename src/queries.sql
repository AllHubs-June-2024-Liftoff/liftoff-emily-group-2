--Create Database
CREATE DATABASE parkd_user_management;

--After creating Database, USE to select database
USE user_management;

--Create Table
CREATE TABLE parkd_users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    
);

--Create Admin user
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';
GRANT ALL PRIVILEGES ON user_management.* TO 'user'@'localhost';
FLUSH PRIVILEGES;