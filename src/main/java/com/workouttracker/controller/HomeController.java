package com.workouttracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

/**
 * Home page controller for the main application pages.
 * Handles requests for home page and dashboard.
 *
 * CST339 Dinesh Gudibandi
 * @author Brandon Ortega, Aaron Starley
 * @version 3.0
 * @since Milestone 4
 */
@Controller
public class HomeController {

    /**
     * Displays the main application home page.
     * This is the landing page users see when they visit the root URL.
     *
     * @return the home page template
     */
    @GetMapping("/")
    public String index() {
        return "pages/index";
    }

    /**
     * Displays the user dashboard after successful login.
     * Redirects to login page if user is not authenticated.
     *
     * @param session HTTP session for checking login status
     * @return dashboard page or redirect to login
     */
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn == null || !loggedIn) {
            return "redirect:/login";
        }
        return "pages/dashboard";
    }
}