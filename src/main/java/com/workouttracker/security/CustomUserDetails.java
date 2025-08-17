package com.workouttracker.security;

import com.workouttracker.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Custom implementation of Spring Security's UserDetails interface.
 * Wraps the application's User entity to provide authentication details.
 *
 * CST-339 Dinesh Gudibandi
 * @author Brandon Ortega
 * @version 1.0
 * @since Milestone 6
 */
public class CustomUserDetails implements UserDetails {

    /**
     * The wrapped User entity from the application
     */
    private final User user;

    /**
     * Constructor that wraps a User entity.
     *
     * @param user The User entity to wrap
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Returns the authorities granted to the user.
     * Currently returns a single ROLE_USER authority for all users.
     * Can be extended to support multiple roles from database.
     *
     * @return Collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // For now, all authenticated users have ROLE_USER
        // This can be extended to read roles from database
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return The user's password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used to authenticate the user.
     *
     * @return The user's username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true if the account is valid (non-expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // We don't implement account expiration
    }

    /**
     * Indicates whether the user is locked or unlocked.
     *
     * @return true if the account is not locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // We don't implement account locking
    }

    /**
     * Indicates whether the user's credentials have expired.
     *
     * @return true if the credentials are valid (non-expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // We don't implement credential expiration
    }

    /**
     * Indicates whether the user is enabled or disabled.
     * Maps to the 'active' field in our User entity.
     *
     * @return true if the user is enabled
     */
    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

    /**
     * Gets the wrapped User entity.
     * Useful for accessing additional user properties not in UserDetails.
     *
     * @return The wrapped User entity
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the user's ID from the wrapped entity.
     * Convenience method for accessing user ID.
     *
     * @return The user's database ID
     */
    public Long getUserId() {
        return user.getId();
    }

    /**
     * Gets the user's email from the wrapped entity.
     * Convenience method for accessing user email.
     *
     * @return The user's email address
     */
    public String getEmail() {
        return user.getEmail();
    }

    /**
     * Gets the user's full name from the wrapped entity.
     * Convenience method for displaying user's name.
     *
     * @return The user's full name
     */
    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }
}