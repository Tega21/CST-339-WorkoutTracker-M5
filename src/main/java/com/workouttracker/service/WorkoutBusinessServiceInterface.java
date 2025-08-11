package com.workouttracker.service;

import com.workouttracker.model.Workout;

import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface WorkoutBusinessServiceInterface {

    boolean createWorkout(Workout workout);
    void updateWorkout(Workout workout, Long userId);
    boolean deleteWorkout(Long id, HttpSession session);
    List<Workout> getAllWorkouts();
	Workout findByIdForUser(Long id, Long userId);

}
