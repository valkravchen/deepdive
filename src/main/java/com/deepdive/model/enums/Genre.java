package com.deepdive.model.enums;

public enum Genre {
    NATURE("Природа"),              // Планета Земля, животный мир
    UNDERWATER("Подводный мир"),     // Команда Кусто, океаны
    SPACE("Космос"),                 // Космос Сагана, астрономия
    HISTORY("История"),              // Исторические документалки
    SCIENCE("Наука"),                // Научные открытия
    TRAVEL("Путешествия"),          // Культуры и страны
    PSYCHOLOGY("Психология"),        // О человеке и мозге
    BIOLOGY("Биология"),            // Эволюция, генетика
    PHYSICS("Физика"),              // Квантовая механика, астрофизика
    ARCHAEOLOGY("Археология");      // Древние цивилизации

    private final String description;

    Genre(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
