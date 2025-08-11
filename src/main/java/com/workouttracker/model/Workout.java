package com.workouttracker.model;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.Date;

/**
 * Represents a workout log entry.
 */
@Table("workouts")
public class Workout {

    @Id  // ADD THIS
    private Long id;

    private Long userId;

    @NotNull(message = "Exercise name is required")
    @Size(min = 2, max = 50, message = "Exercise name must be between 2 and 50 characters")
    private String exerciseName;

    @NotNull(message = "Muscle group is required")
    private String muscleGroup;

    @NotNull(message = "Sets is required")
    @Min(value = 1, message = "Sets must be at least 1")
    @Max(value = 50, message = "Sets cannot exceed 50")
    private Integer sets;

    @NotNull(message = "Reps is required")
    @Min(value = 1, message = "Reps must be at least 1")
    @Max(value = 100, message = "Reps cannot exceed 100")
    private Integer reps;

    @Min(value = 0, message = "Weight cannot be negative")
    private Double weight;

    private Date workoutDate;

    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    private String notes;

    // Default constructor
    public Workout() {
        this.workoutDate = new Date();
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // ADD THESE TWO METHODS
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getExerciseName() { return exerciseName; }
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }

    public String getMuscleGroup() { return muscleGroup; }
    public void setMuscleGroup(String muscleGroup) { this.muscleGroup = muscleGroup; }

    public Integer getSets() { return sets; }
    public void setSets(Integer sets) { this.sets = sets; }

    public Integer getReps() { return reps; }
    public void setReps(Integer reps) { this.reps = reps; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Date getWorkoutDate() { return workoutDate; }
    public void setWorkoutDate(Date workoutDate) { this.workoutDate = workoutDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}