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
    }
}
