package com.maxogod.gymchadserver.controller;

import com.maxogod.gymchadserver.model.Activity;
import com.maxogod.gymchadserver.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        Activity newActivity = this.service.createActivity(activity);
        if (newActivity == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(newActivity);
    }

    @GetMapping("/")
    public ResponseEntity<List<Activity>> getActivities() {
        return ResponseEntity.ok(this.service.getActivities());
    }

    @GetMapping("/{activityId}")
    public void getActivity(@PathVariable String activityId) {
//        this.service.getActivity();
    }

    @PostMapping("/{activityId}/add-exercise")
    public void addExercise(@PathVariable String activityId) {
//        this.service.addExercise();
    }

    @PutMapping("/{activityId}")
    public void updateActivity(@PathVariable String activityId) {
//        this.service.updateActivity();
    }

    @DeleteMapping("/{activityId}")
    public void deleteActivity(@PathVariable String activityId) {
//        this.service.deleteActivity();
    }

}
