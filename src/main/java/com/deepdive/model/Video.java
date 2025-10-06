package com.deepdive.model;

import com.deepdive.model.enums.MediaType;

public class Video extends MediaContent {
    private String director;
    private int durationMinutes;

    public Video(String title, int year, MediaType type) {
        super(title, year, type);
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
