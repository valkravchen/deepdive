package com.deepdive.model.enums;

public enum Genre {
    NATURE("Природа"),
    OCEAN("Океан"),
    SPACE("Космос"),
    HISTORY("История"),
    SCIENCE("Наука"),
    WILDLIFE("Животный мир");

    private final String description;

    Genre(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
