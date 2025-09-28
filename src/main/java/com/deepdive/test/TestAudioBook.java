package com.deepdive.test;

import com.deepdive.model.AudioBook;

public class TestAudioBook {
    public static void main(String[] args) {
        System.out.println("=== Тест AudioBook ===\n");
        AudioBook audioBook = new AudioBook("Дыхание", "Джеймс Нестор", 2020);
        audioBook.setNarrator("Сергей Чонишвили");
        audioBook.setDurationMinutes(390);
        audioBook.setPublisher("МИФ");
        System.out.println("Аудиокнига: " + audioBook.getFormattedInfo());
        System.out.println("Прогресс начальный: " + audioBook.getProgress() + "%");
        System.out.println("Осталось: " + audioBook.getTimeRemaining());
        audioBook.updateListeningProgress(130);
        System.out.println("\nПрослушано 2ч 10мин:");
        System.out.println("Прогресс: " + String.format("%.1f%%", audioBook.getProgress()));
        System.out.println("Осталось: " + audioBook.getTimeRemaining());
        System.out.println("Завершено: " + audioBook.isCompleted());
        audioBook.updateListeningProgress(390);
        System.out.println("\nПрослушано полностью:");
        System.out.println("Прогресс: " + audioBook.getProgress() + "%");
        System.out.println("Завершено: " + audioBook.isCompleted());
        AudioBook noNarrator = new AudioBook("Космос", "Карл Саган", 1980);
        noNarrator.setDurationMinutes(840);
        System.out.println("\nБез диктора: " + noNarrator.getFormattedInfo());
        audioBook.setDurationMinutes(0);
        System.out.println("Прогресс при 0 минут: " + audioBook.getProgress() + "%");
        System.out.println("\n✓ Тест AudioBook пройден!");
    }
}
