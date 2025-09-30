package com.deepdive.test;

import com.deepdive.collection.MediaCatalog;
import com.deepdive.model.*;
import com.deepdive.model.enums.*;

import java.util.List;

public class TestMediaCatalog {
    public static void main(String[] args) {
        System.out.println("=== Тест MediaCatalog ===\n");
        MediaCatalog catalog = new MediaCatalog();
        Video planetEarth = new Video("Планета Земля", 2006, MediaType.DOCUMENTARY_SERIES);
        planetEarth.setDirector("Дэвид Аттенборо");
        planetEarth.addGenre(Genre.NATURE);
        planetEarth.addTag("BBC");
        Book sapiens = new Book("Sapiens", "Юваль Харари", 2011);
        sapiens.addGenre(Genre.HISTORY);
        sapiens.addGenre(Genre.BIOLOGY);
        AudioBook breath = new AudioBook("Дыхание", "Джеймс Нестор", 2020);
        breath.addGenre(Genre.BIOLOGY);
        breath.addGenre(Genre.SCIENCE);
        System.out.println("Добавление контента:");
        System.out.println("Добавлен сериал: " + catalog.addMedia(planetEarth));
        System.out.println("Добавлена книга: " + catalog.addMedia(sapiens));
        System.out.println("Добавлена аудиокнига: " + catalog.addMedia(breath));
        System.out.println("Добавлен null: " + catalog.addMedia(null));
        System.out.println("Всего элементов: " + catalog.getTotalItems());
        String id = planetEarth.getId();
        MediaContent found = catalog.findById(id);
        System.out.println("\nПоиск по ID: " + (found != null ? "найден" : "не найден"));
        List<MediaContent> results = catalog.findByTitle("дыхание");
        System.out.println("Поиск 'дыхание': найдено " + results.size());
        results = catalog.findByType(MediaType.BOOK);
        System.out.println("Книг в каталоге: " + results.size());
        results = catalog.findByGenre(Genre.BIOLOGY);
        System.out.println("Контента по биологии: " + results.size());
        System.out.println();
        catalog.printStatistics();
        System.out.println("\nУдаление по ID: " + catalog.removeMedia(id));
        System.out.println("Удаление несуществующего: " + catalog.removeMedia("fake-id"));
        System.out.println("Осталось элементов: " + catalog.getTotalItems());
        System.out.println();
        catalog.printCatalog();
        System.out.println("\n✓ Тест MediaCatalog пройден!");
        System.out.println("\n=== Тест управления порядком ===");
        catalog = new MediaCatalog();
        catalog.addMedia(new Book("A", "Автор", 2020));
        catalog.addMedia(new Book("B", "Автор", 2021));
        catalog.addMedia(new Book("C", "Автор", 2022));
        catalog.addMedia(new Book("D", "Автор", 2023));
        System.out.println("Исходный порядок:");
        for (int i = 0; i < 4; i++) {
            System.out.print(catalog.getByPosition(i).getTitle() + " ");
        }
        catalog.moveUp(2);  // C вверх
        System.out.println("\nПосле moveUp(2):");
        for (int i = 0; i < 4; i++) {
            System.out.print(catalog.getByPosition(i).getTitle() + " ");
        }
        catalog.swap(0, 3);
        System.out.println("\nПосле swap(0, 3):");
        for (int i = 0; i < 4; i++) {
            System.out.print(catalog.getByPosition(i).getTitle() + " ");
        }
        catalog.moveToTop(2);
        System.out.println("\nПосле moveToTop(2):");
        for (int i = 0; i < 4; i++) {
            System.out.print(catalog.getByPosition(i).getTitle() + " ");
        }
        System.out.println("\n✓ Управление порядком работает!");
        System.out.println("\n=== Тест пагинации ===");

// Создаем каталог с 15 элементами
        catalog = new MediaCatalog();
        for (int i = 1; i <= 15; i++) {
            catalog.addMedia(new Book("Книга " + i, "Автор", 2000 + i));
        }

// Тест getPage
        List<MediaContent> page1 = catalog.getPage(0, 5);
        System.out.println("Страница 0 (размер 5): " + page1.size() + " элементов");

        List<MediaContent> page2 = catalog.getPage(1, 5);
        System.out.println("Страница 1: " + page2.get(0).getTitle());

// Тест getTotalPages
        System.out.println("Всего страниц (по 5): " + catalog.getTotalPages(5));
        System.out.println("Всего страниц (по 7): " + catalog.getTotalPages(7));

// Тест printPage
        System.out.println();
        catalog.printPage(1, 5);

// Тест пустой страницы
        catalog.printPage(5, 5);

        System.out.println("\n✓ Пагинация работает!");

    }
}
