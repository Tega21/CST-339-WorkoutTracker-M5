package com.workouttracker.service;

import com.workouttracker.model.LoginPrincipal;
import com.workouttracker.model.User;

public interface UserBusinessServiceInterface {
    boolean authenticateUser(LoginPrincipal loginModel);
    boolean registerUser(User user);
}
