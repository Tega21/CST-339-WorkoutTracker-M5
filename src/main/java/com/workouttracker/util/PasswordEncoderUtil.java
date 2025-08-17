package com.workouttracker.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility class for password encoding operations.
 * Provides methods to encode passwords and verify encoded passwords.
 *
 * CST-339 Dinesh Gudibandi
 * @author Brandon Ortega
 * @version 1.0
 * @since Milestone 6
 */
public class PasswordEncoderUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Encodes a raw password using BCrypt.
     *
     * @param rawPassword The plain text password
     * @return The encoded password
     */
    public static String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * Verifies if a raw password matches an encoded password.
     *
     * @param rawPassword The plain text password
     * @param encodedPassword The encoded password from database
     * @return true if passwords match, false otherwise
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    /**
     * Main method for testing password encoding.
     * Can be used to generate encoded passwords for database seeding.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Example: Generate encoded password for demo user
        String demoPassword = "password123";
        String encodedPassword = encodePassword(demoPassword);

        System.out.println("Original password: " + demoPassword);
        System.out.println("Encoded password: " + encodedPassword);
        System.out.println("Password matches: " + matches(demoPassword, encodedPassword));

        // Generate encoded passwords for testing
        System.out.println("\n=== Encoded Passwords for Database ===");
        System.out.println("'password123' -> " + encodePassword("password123"));
        System.out.println("'admin123' -> " + encodePassword("admin123"));
        System.out.println("'test123' -> " + encodePassword("test123"));
    }
}