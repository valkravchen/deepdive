package com.deepdive.test;

import com.deepdive.model.Book;

public class TestBook {
    public static void main(String[] args) {
        System.out.println("=== Тест Book ===\n");
        Book book = new Book("Sapiens", "Юваль Ной Харари", 2011);
        book.setTotalPages(443);
        book.setPublisher("Синдбад");
        book.setIsbn("978-5-00131-596-0");
        System.out.println("Книга: " + book.getFormattedInfo());
        System.out.println("Прогресс начальный: " + book.getProgress() + "%");
        System.out.println("Осталось страниц: " + book.getPagesRemaining());
        book.updateProgress(100);
        System.out.println("\nПрочитано 100 страниц:");
        System.out.println("Прогресс: " + String.format("%.1f%%", book.getProgress()));
        System.out.println("Осталось: " + book.getPagesRemaining());
        System.out.println("Завершено: " + book.isCompleted());
        book.updateProgress(443);
        System.out.println("\nПрочитано 443 страницы:");
        System.out.println("Прогресс: " + book.getProgress() + "%");
        System.out.println("Завершено: " + book.isCompleted());
        book.setTotalPages(0);
        System.out.println("\nПрогресс при 0 страниц: " + book.getProgress() + "%");
        System.out.println("\n✓ Тест Book пройден!");
    }
}
