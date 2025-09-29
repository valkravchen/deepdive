package com.deepdive.test;

import com.deepdive.collection.MediaCatalog;
import com.deepdive.model.*;
import com.deepdive.model.enums.MediaType;

public class TestMediaCatalogList {
    public static void main(String[] args) {
        System.out.println("=== Тест позиционного доступа ===\n");
        MediaCatalog catalog = new MediaCatalog();
        // Создаем тестовый контент
        Video planetEarth = new Video("Планета Земля", 2006, MediaType.DOCUMENTARY_SERIES);
        Book sapiens = new Book("Sapiens", "Юваль Харари", 2011);
        AudioBook breath = new AudioBook("Дыхание", "Джеймс Нестор", 2020);
        // Добавляем в каталог
        catalog.addMedia(planetEarth);
        catalog.addMedia(sapiens);
        catalog.addMedia(breath);
        System.out.println("Элемент на позиции 1: " + catalog.getByPosition(1).getTitle());
        Video oceans = new Video("Океаны", 2009, MediaType.DOCUMENTARY);
        catalog.insertAt(1, oceans);
        System.out.println("После вставки на позицию 1: " +
                catalog.getByPosition(1).getTitle());
        System.out.println("Всего элементов: " + catalog.getTotalItems());
        Book otherBook = new Book("Космос", "Карл Саган", 1980);
        MediaContent replaced = catalog.replaceAt(2, otherBook);
        System.out.println("Заменен элемент: " + replaced.getTitle());
        System.out.println("Новый на позиции 2: " +
                catalog.getByPosition(2).getTitle());
        int pos = catalog.getPosition(breath);
        System.out.println("Позиция 'Дыхание': " + pos);
        MediaContent removed = catalog.removeAt(1);
        System.out.println("Удален с позиции 1: " + removed.getTitle());
        System.out.println("Осталось элементов: " + catalog.getTotalItems());
        try {
            catalog.getByPosition(10);
            System.out.println("ОШИБКА: Должно быть исключение!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Исключение поймано: " + e.getMessage());
        }

        System.out.println("\n✓ Тест позиционного доступа пройден!");
    }
}

