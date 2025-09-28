package com.deepdive.test;

import com.deepdive.model.Video;
import com.deepdive.model.enums.MediaType;

public class TestVideo {
    public static void main(String[] args) {
        System.out.println("=== Тест Video ===\n");
        Video film = new Video("Океаны", 2009, MediaType.DOCUMENTARY);
        film.setDirector("Жак Перрен");
        film.setDurationMinutes(104);
        film.setStudio("Pathé");
        System.out.println("Фильм: " + film.getFormattedInfo());
        System.out.println("Длительность: " + film.getFormattedDuration());
        System.out.println("Это сериал: " + film.isSeries());
        System.out.println("Прогресс до просмотра: " + film.getProgress() + "%");
        film.setCompleted(true);
        System.out.println("Прогресс после просмотра: " + film.getProgress() + "%");
        System.out.println("\n--- Тест сериала ---");
        Video series = new Video("Планета Земля", 2006, MediaType.DOCUMENTARY_SERIES);
        series.setDirector("Дэвид Аттенборо");
        series.setEpisodesTotal(11);
        series.setEpisodesWatched(3);
        series.setStudio("BBC");
        System.out.println("Сериал: " + series.getFormattedInfo());
        System.out.println("Это сериал: " + series.isSeries());
        System.out.println("Прогресс (3 из 11): " + String.format("%.1f%%", series.getProgress()));
        series.setEpisodesTotal(0);
        System.out.println("Прогресс при 0 эпизодах: " + series.getProgress() + "%");
        try {
            Video wrongType = new Video("Тест", 2020, MediaType.BOOK);
            System.out.println("ОШИБКА: Должно было быть исключение!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nИсключение поймано корректно: " + e.getMessage());
        }
        System.out.println("\n✓ Тест Video пройден!");
    }
}

