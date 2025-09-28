package com.deepdive.model;

import com.deepdive.model.enums.MediaType;

public class AudioBook extends MediaContent {
    private String author;               // автор
    private String narrator;             // диктор/чтец
    private int durationMinutes;         // длительность в минутах
    private int currentMinute;           // текущая позиция прослушивания
    private String language;             // язык аудиокниги
    private String publisher;            // издатель аудиокниги

    public AudioBook(String title, String author, int year) {
        super(title, year, MediaType.AUDIOBOOK);
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Автор не может быть пустым");
        }
        this.author = author;
    }

    @Override
    public String getFormattedInfo() {
        StringBuilder info = new StringBuilder();
        info.append(getType().getDescription()).append(": ")
                .append(getTitle()).append(", ")
                .append(author).append(" (")
                .append(getYear()).append(")");
        if (narrator != null && !narrator.trim().isEmpty()) {
            info.append(", читает ").append(narrator);
        }
        return info.toString();
    }

    @Override
    public double getProgress() {
        return (durationMinutes == 0) ? 0.0 : (double) (currentMinute * 100) / durationMinutes;
    }

    public void updateListeningProgress(int minutesListened) {
        if (minutesListened < 0) {
            throw new IllegalArgumentException("Минута прослушивания не может быть отрицательной");
        }
        currentMinute = minutesListened;
        setCompleted(currentMinute >= durationMinutes && durationMinutes > 0);
    }

    public String getTimeRemaining() {
        int timeRemainingMinutes = (currentMinute > durationMinutes) ? 0 : (durationMinutes - currentMinute);
        return  (timeRemainingMinutes / 60) + "ч " + (timeRemainingMinutes % 60) + "мин";
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
        if (durationMinutes < 0) {
            throw new IllegalArgumentException("Длительность не может быть отрицательной");
        }
        this.durationMinutes = durationMinutes;
    }

    public int getCurrentMinute() {
        return currentMinute;
    }

    public void setCurrentMinute(int currentMinute) {
        this.currentMinute = currentMinute;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
