package com.workouttracker.model;

import jakarta.validation.constraints.NotBlank;

/**
 * Simple login form object for handling user authentication.
 * Contains username and password from login form.
 *
 * CST339 Dinesh Gudibandi
 * @author Brandon Ortega, Aaron Starley
 * @version 3.0
 * @since Milestone 4
 */
public class LoginPrincipal {

    /**
     * Username entered in login form
     */
    @NotBlank(message = "Username is required")
    private String username;

    /**
     * Password entered in login form
     */
    @NotBlank(message = "Password is required")
    private String password;

    /**
     * Whether authentication was successful
     */
    private boolean authenticated = false;

    /**
     * The authenticated user object (null if authentication failed)
     */
    private User user;

    /**
     * Default constructor required by Spring for form binding.
     */
    public LoginPrincipal() {}

    /**
     * Constructor with username and password.
     *
     * @param username the username
     * @param password the password
     */
    public LoginPrincipal(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if authentication was successful.
     *
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * Sets the authentication status.
     *
     * @param authenticated true if authenticated, false otherwise
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    /**
     * Gets the authenticated user.
     *
     * @return the user object, or null if not authenticated
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the authenticated user.
     *
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns string representation for debugging.
     * DOES NOT INCLUDE PASSWORD FOR SECURITY REASONS
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "LoginPrincipal{username='" + username + "', authenticated=" + authenticated + "}";
    }
}