package com.workouttracker.controller;

import com.workouttracker.model.User;
import com.workouttracker.service.UserBusinessServiceInterface;
import com.workouttracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

/**
 * Registration controller for handling user registration.
 * Manages display and processing of user registration forms.
 *
 * @author Brandon Ortega, Aaron Starley
 * @version 1.0
 * @since Milestone 2
 */
@Controller
@RequestMapping("/register")
public class UserController {

    /**
     * UserService for handling user-related operations
     */
    @Autowired
    private UserBusinessServiceInterface userService;

    /**
     * Displays the user registration form.
     * Creates an empty User object for form binding.
     *
     * @param model Spring model for passing data to view
     * @return registration form template
     */
    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "pages/register";
    }

    /**
     * Processes the submitted registration form.
     * Validates user input and creates new user account.
     *
     * @param user the user object bound from form data
     * @param result validation results
     * @param model Spring model for passing data to view
     * @param redirectAttributes for flash messages on redirect
     * @return registration form (on error) or redirect to login (on success)
     */
    @PostMapping
    public String processForm(@Valid @ModelAttribute User user,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        // Checking validation errors
        if (result.hasErrors()) {
            return "pages/register";
        }

        try {
            userService.registerUser(user);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Registration successful! Please log in.");
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "pages/register";
        }
    }
}