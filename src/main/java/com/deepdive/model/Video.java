package com.deepdive.model;

import com.deepdive.model.enums.MediaType;

public class Video extends MediaContent {
    private int durationMinutes;        // продолжительность в минутах
    private String director;             // режиссер
    private String productionCountry;    // страна производства
    private String studio;              // студия
    private String language;             // язык оригинала
    private int episodesTotal;          // всего эпизодов (для сериалов)
    private int episodesWatched;        // просмотрено эпизодов

    public Video(String title, int year, MediaType type) {
        super(title, year, type);
        if (type != MediaType.DOCUMENTARY && type != MediaType.DOCUMENTARY_SERIES) {
            throw new IllegalArgumentException(
                    "Video может быть только DOCUMENTARY или DOCUMENTARY_SERIES, получен: " + type.name()
            );
        }
    }

    @Override
    public String getFormattedInfo() {
        StringBuilder info = new StringBuilder();
        info.append(getType().getDescription()).append(": ")
                .append(getTitle()).append(" (")
                .append(getYear()).append("), ")
                .append(durationMinutes).append(" мин");
        if (studio != null) {
            info.append(", ").append(studio);
        }
        return info.toString();
    }

    @Override
    public double getProgress() {
        if (getType() == MediaType.DOCUMENTARY) {
            return isCompleted() ? 100 : 0.0;
        }
        return (episodesTotal == 0) ? 0.0 : (double) (episodesWatched * 100) / episodesTotal;
    }

    public boolean isSeries() {
        return getType() == MediaType.DOCUMENTARY_SERIES;
    }

    public String getFormattedDuration() {
        return (durationMinutes / 60) + "ч " + (durationMinutes % 60) + "мин";
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        if (durationMinutes < 0) {
            throw new IllegalArgumentException("Продолжительность не может быть отрицательной");
        }
        this.durationMinutes = durationMinutes;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProductionCountry() {
        return productionCountry;
    }

    public void setProductionCountry(String productionCountry) {
        this.productionCountry = productionCountry;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getEpisodesTotal() {
        return episodesTotal;
    }

    public void setEpisodesTotal(int episodesTotal) {
        this.episodesTotal = episodesTotal;
    }

    public int getEpisodesWatched() {
        return episodesWatched;
    }

    public void setEpisodesWatched(int episodesWatched) {
        if (episodesWatched < 0) {
            throw new IllegalArgumentException("Количество просмотренных эпизодов не может быть отрицательным");
        }
        if (episodesWatched > episodesTotal) {
            throw new IllegalArgumentException("Нельзя посмотреть больше эпизодов, чем есть всего");
        }
        this.episodesWatched = episodesWatched;
    }
}
