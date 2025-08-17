-- Drop tables if they exist (for clean setup)
DROP TABLE IF EXISTS workouts;
DROP TABLE IF EXISTS users;

-- Users table for login/registration
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       first_name VARCHAR(50),
                       last_name VARCHAR(50),
                       email VARCHAR(100) UNIQUE NOT NULL,
                       phone_number VARCHAR(20),
                       created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       active BOOLEAN DEFAULT TRUE
);

-- Workouts table
CREATE TABLE workouts (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          exercise_name VARCHAR(100) NOT NULL,
                          muscle_group VARCHAR(50),
                          sets INT,
                          reps INT,
                          weight DECIMAL(5,2),
                          workout_date DATE,
                          notes TEXT,
                          created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert demo user for testing
INSERT INTO users (username, password, first_name, last_name, email)
VALUES ('demo', 'password123', 'Demo', 'User', 'demo@test.com');