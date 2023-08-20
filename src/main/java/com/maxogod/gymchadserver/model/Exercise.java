package com.maxogod.gymchadserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("exercises")
public class Exercise implements Serializable {

    @Id
    private String id;

    private String name;

    private String picture;

    private String description;

    private int sets;

    private int reps;

    private int time;

    public Exercise() {
    }

    public Exercise(String name, String picture, String description, int sets, int reps, int time) {
        this.name = name;
        this.picture = picture;
        this.description = description;
        this.sets = sets;
        this.reps = reps;
        this.time = time;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
