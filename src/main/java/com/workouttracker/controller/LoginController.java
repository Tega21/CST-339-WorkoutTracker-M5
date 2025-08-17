package com.workouttracker.controller;

import com.workouttracker.model.LoginPrincipal;
import com.workouttracker.model.User;
import com.workouttracker.service.UserBusinessServiceInterface;
import com.workouttracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Login controller for handling user authentication.
 * Manages login form display, authentication, and logout.
 *
 * CST-339 Dinesh Gudibandi
 * @author Brandon Ortega, Aaron Starley
 * @version 3.0
 * @since Milestone 4
 */
@Controller
public class LoginController {

    /**
     * UserService for user lookup and authentication
     */
    @Autowired
    private UserBusinessServiceInterface userService;

    /**
     * Displays the login form.
     * Creates empty LoginPrincipal object for form binding.
     *
     * @param model Spring model for passing data to view
     * @return login form template
     */
    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("loginPrincipal", new LoginPrincipal());
        return "pages/login";
    }

    /**
     * Processes login form submission.
     * Authenticates user credentials and manages session.
     *
     * @param loginPrincipal login form data
     * @param result validation results
     * @param model Spring model for passing data to view
     * @param session HTTP session for storing login state
     * @return login form (on failure) or redirect to dashboard (on success)
     */
    @PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute LoginPrincipal loginPrincipal,
                               BindingResult result,
                               Model model,
                               HttpSession session) {
        if (result.hasErrors()) {
            return "pages/login";
        }

        if (userService.authenticateUser(loginPrincipal)) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("currentUser", loginPrincipal.getUsername());
            session.setAttribute("userId", loginPrincipal.getUser().getId()); // ADD THIS LINE
            return "redirect:/dashboard";
        }

        model.addAttribute("errorMessage", "Invalid credentials");
        return "pages/login";
    }

    /**
     * Handles user logout.
     * Clears session data and redirects to home page.
     *
     * @param session HTTP session to invalidate
     * @return redirect to home page
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}