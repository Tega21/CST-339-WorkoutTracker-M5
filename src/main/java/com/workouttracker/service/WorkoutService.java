package com.workouttracker.service;

import com.workouttracker.model.Workout;
import com.workouttracker.repository.WorkoutRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Workout management service using database.
 * Handles workout CRUD operations.
 *
 * @author Brandon Ortega, Aaron Starley
 * @version 2.0
 * @since Milestone 4
 */
@Service
public class WorkoutService implements WorkoutBusinessServiceInterface {

    @Autowired
    private WorkoutRepository workoutRepository;

    /**
     * Creates new workout in database.
     *
     * @param workout the workout to create
     * @return true if creation successful
     */
    @Override
    public boolean createWorkout(Workout workout) {
        workoutRepository.save(workout);
        System.out.println("Workout saved to database: " + workout.getExerciseName());
        return true;
    }
    
    
    public boolean deleteWorkout(Long id, HttpSession session) {
    	try {
    		Optional<Workout> workout = workoutRepository.findById(id);
    		if(workout == null) {
    			System.out.println("Workout not found!");
    			return false;
    		} else {
    			System.out.println("Deleting workout: " + id);
    			workoutRepository.deleteById(id);
    			return true;
    		}
    		
    	} catch (Exception e) {
    		return false;
    	}
    }

    /**
     * Gets all workouts from database.
     *
     * @return list of all workouts
     */
    @Override
    public List<Workout> getAllWorkouts() {
        List<Workout> workouts = new ArrayList<>();
        workoutRepository.findAll().forEach(workouts::add);
        return workouts;
    }

    /**
     * Gets workouts for specific user.
     *
     * @param userId the user ID
     * @return list of user's workouts
     */
    public List<Workout> getUserWorkouts(Long userId) {
        return workoutRepository.findByUserId(userId);
    }
    public Workout findByIdForUser(Long id, Long userId) {
        return workoutRepository.findById(id)
                .filter(w -> w.getUserId().equals(userId))
                .orElse(null);
    }

    public void updateWorkout(Workout updated, Long userId) {
        Workout existing = findByIdForUser(updated.getId(), userId);
        if (existing == null) return;
        existing.setExerciseName(updated.getExerciseName());
        existing.setMuscleGroup(updated.getMuscleGroup());
        existing.setSets(updated.getSets());
        existing.setReps(updated.getReps());
        existing.setWeight(updated.getWeight());
        existing.setWorkoutDate(updated.getWorkoutDate());
        existing.setNotes(updated.getNotes());
        workoutRepository.save(existing);
    }       
}