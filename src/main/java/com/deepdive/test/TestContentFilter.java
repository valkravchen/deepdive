package com.deepdive.test;

import com.deepdive.collection.MediaCatalog;
import com.deepdive.model.*;
import com.deepdive.model.enums.*;
import com.deepdive.util.ContentFilter;

public class TestContentFilter {
    public static void main(String[] args) {
        System.out.println("=== Тест ContentFilter ===\n");

        // Создаем тестовый каталог
        MediaCatalog catalog = new MediaCatalog();

        // Добавляем тестовые данные
        catalog.addMedia(new Video("Космос", 1980, MediaType.DOCUMENTARY_SERIES));
        catalog.addMedia(new Book("Sapiens", "Харари", 2011));
        catalog.addMedia(new Video("Планета Земля", 2006, MediaType.DOCUMENTARY_SERIES));
        catalog.addMedia(new Book("Дыхание", "Нестор", 2020));
        catalog.addMedia(new Video("Голубая планета", 2001, MediaType.DOCUMENTARY_SERIES));
        catalog.addMedia(new Book("Sapiens", "Харари", 2011)); // дубликат
        catalog.addMedia(new Video("Океаны", 2009, MediaType.DOCUMENTARY));
        System.out.println("Исходный каталог: " + catalog.getTotalItems() + " элементов");
        System.out.println("\n=== Тест removeOldContent ===");
        int totalBefore = catalog.getTotalItems();
        int removed = ContentFilter.removeOldContent(catalog, 2000);
        System.out.println("Удалено контента до 2000 года: " + removed);
        System.out.println("Осталось в каталоге: " + catalog.getTotalItems());
    }
}
