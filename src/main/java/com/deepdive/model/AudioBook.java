package com.deepdive.model;

import com.deepdive.model.enums.MediaType;

public class AudioBook extends MediaContent {
    private String author;
    private String narrator;
    private int durationMinutes;

    public AudioBook(String title,String author, String narrator, int year) {
        super(title, year, MediaType.AUDIOBOOK);
        this.author = author;
        this.narrator = narrator;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
