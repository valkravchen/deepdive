package com.deepdive.test;

import com.deepdive.collection.MediaCatalog;
import com.deepdive.model.*;
import com.deepdive.model.enums.*;

import java.util.List;

public class FinalTest {
    public static void main(String[] args) {
        System.out.println("=== ФИНАЛЬНЫЙ ТЕСТ ШАГА 1 ===\n");
        runAllTests();
    }

    private static void runAllTests() {
        int passed = 0;
        int total = 8;
        if (testEnum()) passed++;
        if (testMediaContent()) passed++;
        if (testArrayList()) passed++;
        if (testForEach()) passed++;
        if (testHashSet()) passed++;
        if (testEquals()) passed++;
        if (testIterator()) passed++;
        if (testComplex()) passed++;
        System.out.println("\n========================================");
        System.out.printf("Результат: %d из %d тестов пройдено%n", passed, total);

        if (passed == total) {
            System.out.println("ВСЕ ТЕСТЫ ПРОЙДЕНЫ! ШАГ 1 ЗАВЕРШЕН!");
        } else {
            System.out.println("Есть проблемы. Проверь код.");
        }
    }

    private static boolean testEnum() {
        System.out.println("Тест 1: Enum");
        MediaType type = MediaType.DOCUMENTARY;
        Genre genre = Genre.NATURE;
        ContentRating rating = ContentRating.EXCELLENT;
        boolean success = type.getDescription().equals("Документальный фильм") &&
                genre.getDescription().equals("Природа") &&
                rating.getStars() == 4;
        System.out.println(success ? "Passed" : "Failed");
        return success;
    }

    private static boolean testMediaContent() {
        System.out.println("\nТест 2: MediaContent + Video + Book + AudioBook");
        Video video = new Video("Test Video", 2020, MediaType.DOCUMENTARY);
        Book book = new Book("Test Book", "Author", 2021);
        AudioBook audio = new AudioBook("Test Audio", "Author", "Narrator", 2022);
        boolean success = video.getTitle().equals("Test Video") &&
                audio.getAuthor().equals("Author") &&
                audio.getNarrator().equals("Narrator");
        System.out.println(success ? "Passed" : "Failed");
        return success;
    }

    private static boolean testArrayList() {
        System.out.println("\nТест 3: ArrayList в MediaCatalog");
        MediaCatalog catalog = new MediaCatalog();
        catalog.addMedia(new Video("Video 1", 2020, MediaType.DOCUMENTARY));
        catalog.addMedia(new Book("Book 1", "Author", 2021));
        boolean success = catalog.getTotalItems() == 2;
        System.out.println(success ? "Passed" : "Failed");
        return success;
    }

    private static boolean testForEach() {
        System.out.println("\nТест 4: for-each поиск");
        MediaCatalog catalog = new MediaCatalog();
        catalog.addMedia(new Video("Video", 2020, MediaType.DOCUMENTARY));
        catalog.addMedia(new Book("Book", "Author", 2020));
        List<MediaContent> year2020 = catalog.findByYear(2020);
        boolean success = year2020.size() == 2;
        System.out.println(success ? "Passed" : "Failed");
        return success;
    }

    private static boolean testHashSet() {
        System.out.println("\nТест 5: HashSet блокирует дубликаты");
        Video video = new Video("Test", 2020, MediaType.DOCUMENTARY);
        video.addGenre(Genre.NATURE);
        video.addGenre(Genre.NATURE);  // Дубликат
        video.addGenre(Genre.WILDLIFE);
        boolean success = video.getGenres().size() == 2;
        System.out.println(success ? "Passed" : "Failed");
        return success;
    }

    private static boolean testEquals() {
        System.out.println("\nТест 6: equals() и hashCode()");
        Video video1 = new Video("Test", 2020, MediaType.DOCUMENTARY);
        Video video2 = new Video("Test", 2020, MediaType.DOCUMENTARY);
        boolean success = video1.equals(video2) &&
                (video1.hashCode() == video2.hashCode());
        System.out.println(success ? "Passed" : "Failed");
        return success;
    }

    private static boolean testIterator() {
        System.out.println("\nТест 7: Iterator безопасное удаление");
        MediaCatalog catalog = new MediaCatalog();
        catalog.addMedia(new Video("Old", 1999, MediaType.DOCUMENTARY));
        catalog.addMedia(new Video("New", 2020, MediaType.DOCUMENTARY));
        catalog.removeOldContent(2000);
        boolean success = catalog.getTotalItems() == 1;
        System.out.println(success ? "Passed" : "Failed");
        return success;
    }

    private static boolean testComplex() {
        System.out.println("\nТест 8: Комплексный сценарий");
        MediaCatalog catalog = new MediaCatalog();
        Video video = new Video("Планета Земля", 2006, MediaType.DOCUMENTARY_SERIES);
        video.addGenre(Genre.NATURE);
        video.addGenre(Genre.WILDLIFE);
        Book book = new Book("Sapiens", "Харари", 2011);
        book.setIsbn("978-0-06-231609-7");
        catalog.addMedia(video);
        catalog.addMedia(book);
        catalog.addMedia(new Video("Old Movie", 1999, MediaType.DOCUMENTARY));
        List<MediaContent> books = catalog.findByType(MediaType.BOOK);
        catalog.removeOldContent(2000);
        boolean success = books.size() == 1 &&
                catalog.getTotalItems() == 2 &&
                video.getGenres().size() == 2;
        System.out.println(success ? "Passed" : "Failed");
        return success;
    }
}
