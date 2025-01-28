--Create Database
CREATE DATABASE parkd_user_management;

--After creating Database, USE to select database
USE parkd_user_management;

--Create Table
CREATE TABLE parkd_users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    
);

--Create Admin user
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'P@ssword123';
GRANT ALL PRIVILEGES ON parkd_user_management.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;



CREATE TABLE registered_users (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL, 
    pw_hash VARCHAR(60) NOT NULL         
);