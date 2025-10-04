package com.deepdive.test;

import com.deepdive.collection.MediaCatalog;
import com.deepdive.model.*;
import com.deepdive.model.enums.MediaType;
import com.deepdive.util.ContentFilter;

import java.util.List;

public class TestContentFilter {
    public static void main(String[] args) {
        System.out.println("=== Тест ContentFilter ===\n");

        // Создаем тестовый каталог
        MediaCatalog catalog = new MediaCatalog();

        // Добавляем тестовые данные
        catalog.addMedia(new Video("Космос", 1980, MediaType.DOCUMENTARY_SERIES));
        catalog.addMedia(new Book("Sapiens", "Харари", 2011));
        catalog.addMedia(new Video("Планета Земля", 2006, MediaType.DOCUMENTARY_SERIES));
        catalog.addMedia(new Book("Краткая история времени", "Хокинг", 1988));
        catalog.addMedia(new Video("Голубая планета", 2001, MediaType.DOCUMENTARY_SERIES));
        catalog.addMedia(new Book("Sapiens", "Харари", 2011)); // дубликат (для Шага 5)
        catalog.addMedia(new Video("Океаны", 2009, MediaType.DOCUMENTARY)); // DOCUMENTARY, не SERIES!

        System.out.println("Исходный каталог: " + catalog.getTotalItems() + " элементов");
        System.out.println("\n=== Тест removeOldContent ===");
        int totalBefore = catalog.getTotalItems();

        // Удаляем контент до 2000 года
        int removed = ContentFilter.removeOldContent(catalog.getItems(), 2000);

        System.out.println("Удалено контента до 2000 года: " + removed);
        System.out.println("Осталось в каталоге: " + catalog.getTotalItems());
        // Восстанавливаем каталог для других тестов
        catalog = new MediaCatalog();
        catalog.addMedia(new Video("Космос", 1980, MediaType.DOCUMENTARY_SERIES));
        catalog.addMedia(new Book("Sapiens", "Харари", 2011));
        catalog.addMedia(new Video("Планета Земля", 2006, MediaType.DOCUMENTARY_SERIES));
        catalog.addMedia(new Video("Океаны", 2009, MediaType.DOCUMENTARY));
        System.out.println("\n=== Тест filterByType ===");
        // Документальные сериалы
        List<MediaContent> series = ContentFilter.filterByType(catalog.getAllMedia(), MediaType.DOCUMENTARY_SERIES);
        System.out.println("Найдено документальных сериалов: " + series.size());
        for (MediaContent v : series) {
            System.out.println("  - " + v.getTitle());
        }
        List<MediaContent> docs = ContentFilter.filterByType(catalog.getAllMedia(), MediaType.DOCUMENTARY);
        System.out.println("\nНайдено документальных фильмов: " + docs.size());
        for (MediaContent v : docs) {
            System.out.println("  - " + v.getTitle());
        }
        List<MediaContent> books = ContentFilter.filterByType(catalog.getAllMedia(), MediaType.BOOK);
        System.out.println("\nНайдено книг: " + books.size());
        for (MediaContent b : books) {
            System.out.println("  - " + b.getTitle());
        }
        // Тест новых методов filterAllVideos и filterAllBooks
        System.out.println("\n=== Тест filterAllVideos ===");
        List<MediaContent> allVideos = ContentFilter.filterAllVideos(catalog.getAllMedia());
        System.out.println("Всего видео (сериалы + фильмы): " + allVideos.size());
        for (MediaContent v : allVideos) {
            System.out.println("  - " + v.getTitle() + " (" + v.getType().getDescription() + ")");
        }
        System.out.println("\n=== Тест filterAllBooks ===");
        List<MediaContent> allBooks = ContentFilter.filterAllBooks(catalog.getAllMedia());
        System.out.println("Всего книг: " + allBooks.size());
        for (MediaContent b : allBooks) {
            System.out.println("  - " + b.getTitle() + " (" + b.getType().getDescription() + ")");
        }
        System.out.println("\n=== Тест removeByType ===");
        totalBefore = catalog.getTotalItems();
        removed = ContentFilter.removeByType(catalog.getItems(), MediaType.DOCUMENTARY_SERIES);
        ContentFilter.printCleanupStats(totalBefore, removed);
        // Тест фильтрации по годам
        System.out.println("\n=== Тест filterByYearRange ===");
        catalog.addMedia(new Book("Книга 2015", "Автор", 2015));
        catalog.addMedia(new Book("Книга 2020", "Автор", 2020));
        List<MediaContent> range = ContentFilter.filterByYearRange(catalog.getAllMedia(), 2009, 2025);
        System.out.println("Контент 2009-2015 гг: " + range.size());
        for (MediaContent content : range) {
            System.out.println("  - " + content.getTitle() + " (" + content.getYear() + ")"
                    +" (" + content.getType().getDescription() + ")");
        }
        System.out.println("\n=== Статистика типов ===");
        String stats = ContentFilter.getTypeStatistics(catalog.getAllMedia());
        System.out.println(stats);
        System.out.println("\n✓ Тест ContentFilter завершен!");
    }
}
