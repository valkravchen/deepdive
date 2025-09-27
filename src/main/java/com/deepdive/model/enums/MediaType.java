package com.deepdive.model.enums;

public enum MediaType {
    DOCUMENTARY("Документальный фильм"),
    DOCUMENTARY_SERIES("Документальный сериал"),
    BOOK("Книга"),
    AUDIOBOOK("Аудиокнига");

    private final String description;

    MediaType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
