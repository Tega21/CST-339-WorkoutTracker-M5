package com.workouttracker.model;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * User class for registration form.
 * CST-339 Dinesh Gudibandi
 * @author Brandon Ortega, Aaron Starley
 * @version 3.0
 * @since Milestone 4
 */
@Table("users")
public class User {

    @Id
    private Long id;

    /**
     * User's first name - required field with length validation
     */
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 32, message = "First name must be between 2 and 32 characters")
    private String firstName;

    /**
     * User's last name - required field with length validation
     */
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 32, message = "Last name must be between 2 and 32 characters")
    private String lastName;

    /**
     * User's email address - required and must be valid email format
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    /**
     * User's phone number - optional field
     */
    private String phoneNumber;

    /**
     * Username for login - required, unique, with length constraints
     */
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    /**
     * Password for login - required with minimum length
     */
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    /**
     * When the user account was created
     */
    private LocalDateTime createdDate;

    /**
     * Whether the user account is active
     */
    private boolean active = true;

    /**
     * Default constructor required by Spring for form binding.
     * Sets creation date to current time.
     */
    public User() {
        this.createdDate = LocalDateTime.now();
    }

    /**
     * Constructor with parameters for easy user creation.
     *
     * @param firstName user's first name
     * @param lastName user's last name
     * @param email user's email address
     * @param username user's login username
     * @param password user's login password
     */
    public User(String firstName, String lastName, String email, String username, String password) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the user's unique ID.
     *
     * @return the user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user's unique ID.
     *
     * @param id the user ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's phone number.
     *
     * @return the phone number (may be null)
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the user's phone number.
     *
     * @param phoneNumber the phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the username for login.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for login.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password for login.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for login.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets when this user account was created.
     *
     * @return the creation date
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets when this user account was created.
     *
     * @param createdDate the creation date to set
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Checks if this user account is active.
     *
     * @return true if active, false if deactivated
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets whether this user account is active.
     *
     * @param active true to activate, false to deactivate
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns a string representation of this user for debugging.
     * Does not include password for security.
     *
     * @return string containing user details
     */
    @Override
    public String toString() {
        return "User{username='" + username + "', email='" + email + "'}";
    }
}