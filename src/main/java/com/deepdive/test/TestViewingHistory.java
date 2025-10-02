package com.deepdive.test;

import com.deepdive.collection.ViewingHistory;
import com.deepdive.model.Video;
import com.deepdive.model.enums.MediaType;

import java.util.List;

public class TestViewingHistory {
    public static void main(String[] args) {
        System.out.println("=== Тест ViewingHistory ===\n");
        ViewingHistory history = new ViewingHistory(5);
        Video video1 = new Video("Планета Земля", 2006, MediaType.DOCUMENTARY_SERIES);
        Video video2 = new Video("Голубая планета", 2001, MediaType.DOCUMENTARY_SERIES);
        Video video3 = new Video("Космос", 1980, MediaType.DOCUMENTARY_SERIES);
        // Тест добавления в историю
        System.out.println("=== Добавление просмотров ===");
        history.addToHistory(video1, 45);
        System.out.println("Добавлен просмотр 1");
        history.addToHistory(video2, 30);
        System.out.println("Добавлен просмотр 2");
        history.addToHistory(video3, 60);
        System.out.println("Добавлен просмотр 3");
        history.addToHistory(video1, 20); // повторный просмотр
        System.out.println("Добавлен просмотр 4 (повтор video1)");
        System.out.println("Размер истории: " + history.getHistorySize());
        System.out.println();
        history.printHistoryInfo();
        System.out.println();

        // Тест получения последних записей
        history.printRecent(3);
        System.out.println();

        // Тест последнего просмотра
        System.out.println("=== Последний просмотр ===");
        ViewingHistory.HistoryEntry last = history.getLastWatched();
        System.out.println("Последнее: " + (last != null ? last.toString() : "null"));
        System.out.println();

        // Тест превышения лимита
        System.out.println("=== Тест ограничения размера ===");
        System.out.println("Макс размер: " + history.getMaxSize());
        history.addToHistory(video2, 25);
        history.addToHistory(video3, 40);
        System.out.println("Добавлено еще 2 просмотра");
        System.out.println("Размер после добавления: " + history.getHistorySize());
        System.out.println("(Должен остаться = " + history.getMaxSize() + ")");
        System.out.println();

        // Тест поиска
        System.out.println("=== Поиск в истории ===");
        List<ViewingHistory.HistoryEntry> found = history.findByTitle("Планета Земля");
        System.out.println("Найдено записей 'Планета Земля': " + found.size());
        for (ViewingHistory.HistoryEntry entry : found) {
            System.out.println("  - " + entry);
        }
        System.out.println();

        // Тест проверки просмотра
        System.out.println("=== Проверка просмотра ===");
        System.out.println("video1 был просмотрен: " + history.wasWatched(video1));
        Video video4 = new Video("Неизвестное", 2020, MediaType.DOCUMENTARY);
        System.out.println("video4 был просмотрен: " + history.wasWatched(video4));
        System.out.println();

        // Тест удаления
        System.out.println("=== Удаление из истории ===");
        int removed = history.removeVideo(video1);
        System.out.println("Удалено записей video1: " + removed);
        System.out.println("Размер после удаления: " + history.getHistorySize());
        System.out.println();

        // Тест статистики
        System.out.println("=== Статистика ===");
        System.out.println("Всего минут просмотрено: " + history.getTotalMinutesWatched());
        System.out.println("Записей в истории: " + history.getHistorySize());
        System.out.println("История пуста: " + history.isEmpty());
        System.out.println();

        // Тест очистки
        System.out.println("=== Очистка истории ===");
        history.clearHistory();
        System.out.println("После clearHistory():");
        System.out.println("Размер: " + history.getHistorySize());
        System.out.println("Пуста: " + history.isEmpty());

        System.out.println("\n✓ Тест ViewingHistory завершен!");
    }
}

