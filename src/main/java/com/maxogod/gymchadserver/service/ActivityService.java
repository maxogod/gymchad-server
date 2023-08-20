package com.maxogod.gymchadserver.service;

import com.maxogod.gymchadserver.model.Activity;
import com.maxogod.gymchadserver.model.Exercise;
import com.maxogod.gymchadserver.model.User;
import com.maxogod.gymchadserver.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository repository;
    private final ExerciseService exerciseService;

    public ActivityService(ActivityRepository repository, ExerciseService exerciseService) {
        this.repository = repository;
        this.exerciseService = exerciseService;
    }

    public Activity createActivity(Activity activity) {
        if (activity.getName().isEmpty() || activity.getExercises() == null) return null;
        Activity newActivity = new Activity();
        newActivity.setName(activity.getName());
        newActivity.setBanner(activity.getBanner());

        List<Exercise> exercises = new ArrayList<>();

        for (Exercise exercise : activity.getExercises()) {
            Exercise newExercise = this.exerciseService.createExercise(exercise);
            if (newExercise == null) continue;
            exercises.add(newExercise);
        }

        newActivity.setExercises(exercises);

        return this.repository.save(newActivity);
    }

    public List<Activity> getActivitiesOfUser(User user) {
        return user.getActivities();
    }

    public Activity getActivity(String activityId) {
        return this.repository.findById(activityId).orElse(null);
    }

    public Activity updateActivity(String activityId, Activity activity) {
        Optional<Activity> optionalActivity = this.repository.findById(activityId);
        if (optionalActivity.isEmpty()) return null;
        Activity updatedActivity = optionalActivity.get();
        updatedActivity.setName(activity.getName());
        updatedActivity.setBanner(activity.getBanner());
        return this.repository.save(updatedActivity);
    }

    public Activity addExercise(String activityId, Exercise exercise) {
        Optional<Activity> optionalActivity = this.repository.findById(activityId);
        if (optionalActivity.isEmpty()) return null;
        Activity activity = optionalActivity.get();
        Exercise newExercise = this.exerciseService.createExercise(exercise);
        if (newExercise == null) return null;
        List<Exercise> exercises = activity.getExercises();
        exercises.add(newExercise);
        activity.setExercises(exercises);
        return this.repository.save(activity);
    }

    public void deleteExerciseFromActivity(String exerciseId) {
        Optional<Activity> optionalActivity = this.repository.findActivityByExerciseId(exerciseId);
        if (optionalActivity.isEmpty()) return;
        Activity activity = optionalActivity.get();
        List<Exercise> exercises = activity.getExercises();
        exercises.removeIf(exercise -> exercise.getId().equals(exerciseId));
        activity.setExercises(exercises);
        this.repository.save(activity);
    }

    public void deleteActivity(String activityId) {
        this.repository.deleteById(activityId);
    }

}
