package com.deepdive.collection;

import com.deepdive.model.MediaContent;
import com.deepdive.model.enums.*;

import java.util.*;

public class MediaCatalog {
    private List<MediaContent> catalog;     // основное хранилище (ArrayList)
    private int totalItems;

    public MediaCatalog() {
        this.catalog = new ArrayList<>();
        this.totalItems = 0;
    }

    public boolean addMedia(MediaContent media) {
        if (media != null) {
            catalog.add(media);
            totalItems++;
            return true;
        }
        return false; // заглушка
    }

    public boolean removeMedia(String id) {
        if (id == null) {
            return false;
        }
        Iterator<MediaContent> iterator = catalog.iterator();
        while (iterator.hasNext()) {
            MediaContent current = iterator.next();
            if (id.equals(current.getId())) {
                iterator.remove();
                totalItems--;
                return true;
            }
        }
        return false;
    }

    public MediaContent findById(String id) {
        for (MediaContent content : catalog) {
            if (id.equals(content.getId())) {
                return content;
            }
        }
        return null;
    }

    // Методы поиска
    public List<MediaContent> findByTitle(String title) {
        List<MediaContent> results = new ArrayList<>();
        if (title == null || title.isEmpty()) {
            return results;
        }
        for (MediaContent content : catalog) {
            if (content.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(content);
            }
        }
        return results;
    }

    public List<MediaContent> findByYear(int year) {
        List<MediaContent> results = new ArrayList<>();
        for (MediaContent content : catalog) {
            if (year == content.getYear()) {
                results.add(content);
            }
        }
        return results;
    }

    public List<MediaContent> findByType(MediaType type) {
        List<MediaContent> results = new ArrayList<>();
        for (MediaContent content : catalog) {
            if (type.equals(content.getType())) {
                results.add(content);
            }
        }
        return results;
    }

    public List<MediaContent> findByGenre(Genre genre) {
        List<MediaContent> results = new ArrayList<>();
        for (MediaContent content : catalog) {
            if (content.getGenres().contains(genre)) {
                results.add(content);
            }
        }
        return results; // заглушка
    }

    // Статистика
    public int getCountByType(MediaType type) {
        int count = 0;
        for (MediaContent content : catalog) {
            if (type.equals(content.getType())) {
                count++;
            }
        }
        return count;
    }

    public void printStatistics() {
        System.out.println("Статистика каталога:");
        for (MediaType type : MediaType.values()) {
            System.out.println("  " + type.getDescription() + ": " + getCountByType(type));
        }
        System.out.println("Всего элементов: " + totalItems);
        System.out.println("Завершено: " + getCompletedCount());
    }

    public int getCompletedCount() {
        int count = 0;
        for (MediaContent content : catalog) {
            if (content.isCompleted()) {
                count++;
            }
        }
        return count; // заглушка
    }

    // Итерация
    public void printCatalog() {
        System.out.println("=== Каталог медиа-контента ===");
        for (MediaContent content : catalog) {
            System.out.println(content.getFormattedInfo());
        }
    }

    public void printWithIterator() {
        System.out.println("=== Каталог (через Iterator) ===");
        Iterator<MediaContent> iterator = catalog.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    // Вспомогательные методы
    public int getTotalItems() {
        return totalItems;
    }

    public List<MediaContent> getAllMedia() {
        return new ArrayList<>(catalog); // заглушка
    }
}
