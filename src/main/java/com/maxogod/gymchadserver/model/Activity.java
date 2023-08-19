package com.maxogod.gymchadserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("activities")
public class Activity {

    @Id
    private String id;

    private String name;

    private String banner;

    private List<Exercise> exercises;

    public Activity() {
    }

    public Activity(String name, String banner, List<Exercise> exercises) {
        this.name = name;
        this.banner = banner;
        this.exercises = exercises;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
