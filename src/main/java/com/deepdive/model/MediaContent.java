package com.deepdive.model;

import com.deepdive.model.enums.MediaType;

import java.util.UUID;

public abstract class MediaContent {
    private final String id;
    private String title;
    private int year;
    private MediaType type;

    public MediaContent(String title, int year, MediaType type) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.year = year;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public MediaType getType() {
        return type;
    }
}
