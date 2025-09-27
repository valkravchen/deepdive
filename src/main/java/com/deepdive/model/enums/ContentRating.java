package com.deepdive.model.enums;

public enum ContentRating {
    MUST_WATCH(5, "Обязательно к просмотру"),
    EXCELLENT(4, "Отличный контент"),
    GOOD(3, "Хороший контент"),
    AVERAGE(2, "Средний контент"),
    POOR(1, "Слабый контент");

    private final int stars;
    private final String description;

    ContentRating(int stars, String description) {
        this.stars = stars;
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public String getDescription() {
        return description;
    }
}
