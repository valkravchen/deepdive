package com.deepdive.model;

import com.deepdive.model.enums.*;

import java.util.*;

public abstract class MediaContent implements Comparable<MediaContent> {
    private final String id;
    private String title;
    private int year;
    private MediaType type;
    private Set<Genre> genres;
    private boolean completed;
    private Set<String> tags;
    private double rating;

    public MediaContent(String title, int year, MediaType type) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.year = year;
        this.type = type;
        this.genres = EnumSet.noneOf(Genre.class);
        this.completed = false;
        this.tags = new HashSet<>();
        this.rating = 0.0;
    }

    public void addGenre(Genre genre) {
        if (genre == null) {
            return;
        }
        genres.add(genre);
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

    public Set<Genre> getGenres() {
        return genres;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = validateRating(rating);
    }

    private static double validateRating(double value) {
        if (value < 0) return 0;
        if (value > 10) return 10;
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MediaContent that = (MediaContent) o;
        return year == that.year &&
                Objects.equals(title, that.title) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, type);
    }

    public boolean addTag(String tag) {
        if (tag == null || tag.trim().isEmpty()) {
            return false;
        }
        return tags.add(tag.trim().toLowerCase());
    }

    public boolean removeTag(String tag) {
        if (tag == null) {
            return false;
        }
        return tags.remove(tag.trim().toLowerCase());
    }

    public Set<String> getTags() {
        return new HashSet<>(tags);
    }

    public boolean hasTag(String tag) {
        if (tag == null) {
            return false;
        }
        return tags.contains(tag.trim().toLowerCase());
    }

    public int getTagCount() {
        return tags.size();
    }

    @Override
    public int compareTo(MediaContent other) {
        int ratingCompare = Double.compare(other.rating, this.rating);
        if (ratingCompare == 0) {
            if (this.title == null && other.title == null) return 0;
            if (this.title == null) return 1;
            if (other.title == null) return -1;
            return this.title.compareTo(other.title);
        }
        return ratingCompare;
    }
}