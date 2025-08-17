package com.workouttracker.security;

import com.workouttracker.model.User;
import com.workouttracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of Spring Security's UserDetailsService.
 * Loads user-specific data from the database for authentication.
 *
 * CST-339 Dinesh Gudibandi
 * @author Brandon Ortega
 * @version 1.0
 * @since Milestone 6
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Repository for accessing user data from database
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Loads user by username for Spring Security authentication.
     * This method is called by Spring Security during the authentication process.
     *
     * @param username The username to search for
     * @return UserDetails object containing user information
     * @throws UsernameNotFoundException if user is not found in database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Attempt to find user in database by username
        User user = userRepository.findByUsername(username);

        // If user not found, throw exception as required by Spring Security
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Log successful user loading (optional - remove in production)
        System.out.println("User found for authentication: " + username);

        // Wrap the User entity in CustomUserDetails for Spring Security
        return new CustomUserDetails(user);
    }

    /**
     * Additional method to load user by email if needed.
     * Can be used for email-based login in the future.
     *
     * @param email The email to search for
     * @return UserDetails object containing user information
     * @throws UsernameNotFoundException if user is not found in database
     */
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new CustomUserDetails(user);
    }
}