package com.deepdive.model;

import com.deepdive.model.enums.Genre;
import com.deepdive.model.enums.MediaType;

import java.util.*;

public abstract class MediaContent {
    private final String id;
    private String title;
    private int year;
    private MediaType type;
    private Set<Genre> genres;

    public MediaContent(String title, int year, MediaType type) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.year = year;
        this.type = type;
        this.genres = new HashSet<>();
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
}

