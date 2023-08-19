package com.maxogod.gymchadserver.service;

import com.maxogod.gymchadserver.model.Activity;
import com.maxogod.gymchadserver.model.Exercise;
import com.maxogod.gymchadserver.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Activity> getActivities() {
        return this.repository.findAll();
    }

}
