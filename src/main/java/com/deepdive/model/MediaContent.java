package com.deepdive.model;

import com.deepdive.model.enums.*;

import java.time.LocalDate;
import java.util.*;

public abstract class MediaContent {
    private final String id;           // уникальный идентификатор (UUID)
    private String title;               // название
    private String originalTitle;       // оригинальное название
    private int year;                   // год выпуска/публикации
    private String description;         // описание
    private Set<Genre> genres;          // жанры (используем HashSet)
    private ContentRating rating;       // личный рейтинг
    private LocalDate dateAdded;        // дата добавления в каталог
    private LocalDate dateWatched;      // дата просмотра/прочтения
    private boolean completed;          // завершено ли
    private Set<String> tags;           // теги (HashSet)
    private MediaType type;            // тип контента

    public MediaContent(String title, int year, MediaType type) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.year = year;
        this.type = type;
        this.genres = new HashSet<>();
        this.tags = new HashSet<>();
        this.dateAdded = LocalDate.now();
    }

    public abstract String getFormattedInfo();

    public abstract double getProgress();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MediaContent that = (MediaContent) o;
        return year == that.year && Objects.equals(title, that.title) && Objects.equals(originalTitle, that.originalTitle) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, originalTitle, year, type);
    }

    @Override
    public String toString() {
        return "MediaContent{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", type=" + type +
                ", genres=" + genres +
                ", tags=" + tags +
                '}';
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

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public ContentRating getRating() {
        return rating;
    }

    public void setRating(ContentRating rating) {
        this.rating = rating;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateWatched() {
        return dateWatched;
    }

    public void setDateWatched(LocalDate dateWatched) {
        this.dateWatched = dateWatched;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public void addGenre(Genre genre) {
        if (genre != null) {
            genres.add(genre);
        }
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
    }

    public void addTag(String tag) {
        if (tag != null && !tag.trim().isEmpty()) {
            tags.add(tag.toLowerCase().trim());
        }
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }
}

