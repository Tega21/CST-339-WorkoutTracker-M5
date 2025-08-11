package com.workouttracker.service;

import com.workouttracker.model.LoginPrincipal;
import com.workouttracker.model.User;
import com.workouttracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User management service using database.
 * Handles user registration and authentication.
 *
 * @author Brandon Ortega, Aaron Starley
 * @version 2.0
 * @since Milestone 4
 */
@Service
public class UserService implements UserBusinessServiceInterface {

    @Autowired
    private UserRepository userRepository;

    /**
     * Authenticates user against database.
     *
     * @param loginModel the login credentials
     * @return true if authentication successful
     */
    @Override
    public boolean authenticateUser(LoginPrincipal loginModel) {
        User user = userRepository.findByUsername(loginModel.getUsername());
        if (user != null && user.getPassword().equals(loginModel.getPassword())) {
            loginModel.setUser(user);
            loginModel.setAuthenticated(true);
            return true;
        }
        return false;
    }

    /**
     * Registers new user in database.
     *
     * @param user the user to register
     * @return true if registration successful
     * @throws RuntimeException if username or email already exists
     */
    @Override
    public boolean registerUser(User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        // Save new user
        userRepository.save(user);
        System.out.println("User registered in database: " + user.getUsername());
        return true;
    }
}