package com.workouttracker.config;

import com.workouttracker.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security configuration class for Workout Tracker application.
 * Configures authentication, authorization, and security filters.
 *
 * CST-339 Dinesh Gudibandi
 * @author Brandon Ortega
 * @version 1.0
 * @since Milestone 6
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Configures the password encoder for secure password storage.
     * Uses BCrypt hashing algorithm with default strength (10).
     *
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the authentication provider with custom user details service.
     * Links the user details service with password encoder for authentication.
     *
     * @return Configured DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Provides the authentication manager bean from Spring Security.
     * Required for processing authentication requests.
     *
     * @param authConfig Spring's authentication configuration
     * @return AuthenticationManager instance
     * @throws Exception if configuration fails
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configures the security filter chain with URL-based authorization rules.
     * Defines which URLs require authentication and which are publicly accessible.
     *
     * @param http HttpSecurity configuration object
     * @return Configured SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configure authorization rules
                .authorizeHttpRequests(authz -> authz
                        // Public pages - no authentication required
                        .requestMatchers("/", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()
                        // All other pages require authentication
                        .anyRequest().authenticated()
                )
                // Configure form-based login
                .formLogin(form -> form
                        .loginPage("/login")                    // Custom login page URL
                        .loginProcessingUrl("/login")           // URL to process login form POST
                        .defaultSuccessUrl("/dashboard", true)  // Redirect after successful login
                        .failureUrl("/login?error=true")        // Redirect on login failure
                        .permitAll()
                )
                // Configure logout
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")                  // Redirect after logout
                        .invalidateHttpSession(true)            // Clear session
                        .deleteCookies("JSESSIONID")            // Delete session cookie
                        .permitAll()
                )
                // Configure session management
                .sessionManagement(session -> session
                        .maximumSessions(1)                     // Only one session per user
                        .expiredUrl("/login?expired=true")      // Redirect if session expires
                )
                // Enable CSRF protection (default in Spring Security)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**") // Ignore for H2 console if used
                );

        return http.build();
    }
}