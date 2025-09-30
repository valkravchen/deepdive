package com.deepdive.collection;

import com.deepdive.model.MediaContent;
import com.deepdive.model.enums.*;

import java.time.LocalDate;
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

    // Получение элемента по позиции
    public MediaContent getByPosition(int index) {
        if (index < 0 || index >= catalog.size()) {
            throw new IndexOutOfBoundsException("Некорректный индекс");
        }
        return catalog.get(index);
    }

    // Замена элемента по позиции
    public MediaContent replaceAt(int index, MediaContent newMedia) {
        if (index < 0 || index >= catalog.size()) {
            throw new IndexOutOfBoundsException("Некорректный индекс");
        }
        if (newMedia == null) {
            throw new IllegalArgumentException("MediaContent не может быть null");
        }
        return catalog.set(index, newMedia);
    }

    // Вставка элемента в конкретную позицию
    public void insertAt(int index, MediaContent media) {
        if (index < 0 || index > catalog.size()) {
            throw new IndexOutOfBoundsException("Некорректный индекс");
        }
        if (media == null) {
            throw new IllegalArgumentException("MediaContent не может быть null");
        }
        catalog.add(index, media);
        totalItems++;
    }

    // Удаление по позиции
    public MediaContent removeAt(int index) {
        if (index < 0 || index >= catalog.size()) {
            throw new IndexOutOfBoundsException("Некорректный индекс");
        }
        totalItems--;
        return catalog.remove(index);
    }

    // Поиск позиции элемента
    public int getPosition(MediaContent media) {
        return catalog.indexOf(media);
    }

    // Поиск последней позиции по названию (может быть несколько с одним названием)
    public int getLastPositionByTitle(String title) {
        if (title == null) {
            return -1;  // или можно искать null элементы
        }
        for (int i = catalog.size() - 1; i >= 0; i--) {
            if (catalog.get(i) != null && title.equals(catalog.get(i).getTitle())) {
                return i;
            }
        }
        return -1;
    }

    // Проверка валидности индекса
    private void checkIndex(int index) {
        if (index < 0 || index >= catalog.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + catalog.size());
        }
    }

    // Перемещение элемента вверх (к началу списка)
    public boolean moveUp(int index) {
        if (index <= 0 || index >= catalog.size()) {
            return false;
        }
        MediaContent element = catalog.get(index);
        catalog.remove(index);
        catalog.add(index - 1, element);
        return true;
    }

    // Перемещение элемента вниз (к концу списка)
    public boolean moveDown(int index) {
        if (index < 0 || index >= catalog.size() - 1) {
            return false;
        }
        MediaContent element = catalog.get(index);
        catalog.remove(index);
        catalog.add(index + 1, element);
        return true;
    }

    // Обмен местами двух элементов
    public void swap(int index1, int index2) {
        if (index1 < 0 || index1 >= catalog.size() ||
                index2 < 0 || index2 >= catalog.size()) {
            throw new IndexOutOfBoundsException("Некорректные индексы");
        }
        if (index1 == index2) {
            return; // Ничего не делаем
        }

        MediaContent element1 = catalog.get(index1);
        MediaContent element2 = catalog.get(index2);
        catalog.set(index1, element2);
        catalog.set(index2, element1);
    }

    // Перемещение в начало
    public void moveToTop(int index) {
        if (index < 0 || index >= catalog.size()) {
            throw new IndexOutOfBoundsException("Некорректный индекс");
        }
        if (index == 0) {
            return; // Уже наверху
        }
        MediaContent element = catalog.get(index);
        catalog.remove(index);
        catalog.add(0, element);
    }

    // Перемещение в конец
    public void moveToBottom(int index) {
        if (index < 0 || index >= catalog.size()) {
            throw new IndexOutOfBoundsException("Некорректный индекс");
        }
        if (index == catalog.size() - 1) {
            return; // Уже внизу
        }
        MediaContent element = catalog.get(index);
        catalog.remove(index);
        catalog.add(element); // В конец
    }

    // Получение страницы каталога
    public List<MediaContent> getPage(int pageNumber, int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Размер страницы должен быть > 0");
        }
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Номер страницы не может быть отрицательным");
        }
        int from = pageNumber * pageSize;
        if (from >= catalog.size()) {
            return new ArrayList<>();
        }
        int to = Math.min(from + pageSize, catalog.size());
        return new ArrayList<>(catalog.subList(from, to));
    }

    // Общее количество страниц
    public int getTotalPages(int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Размер страницы должен быть > 0");
        }
        return (catalog.size() + pageSize - 1) / pageSize;
    }

    // Информация о странице
    public String getPageInfo(int pageNumber, int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Размер страницы должен быть > 0");
        }
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Номер страницы не может быть отрицательным");
        }
        int totalPages = getTotalPages(pageSize);
        int totalElements = catalog.size();
        int startElement = pageNumber * pageSize + 1;
        int endElement = Math.min(startElement + pageSize - 1, totalElements);
        return "Страница " + (pageNumber + 1) + " из " + totalPages +
                " (элементы " + startElement + "-" + endElement + " из " + totalElements + ")";
    }


    public void printPage(int pageNumber, int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Размер страницы должен быть > 0");
        }
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Номер страницы не может быть отрицательным");
        }
        System.out.println(getPageInfo(pageNumber, pageSize));
        System.out.println("-".repeat(40));
        List<MediaContent> page = getPage(pageNumber, pageSize);
        int startNum = pageNumber * pageSize + 1;
        if (page.isEmpty()) {
            System.out.println("Страница пуста");
        } else {
            for (int i = 0; i < page.size(); i++) {
                System.out.println((startNum + i) + ". " + page.get(i).getTitle() + " (" +
                        page.get(i).getYear() + ") - " + page.get(i).getType());
            }
        }
    }

    // Поиск и замена всех элементов по условию
    public int replaceAll(String oldTitle, String newTitle) {
        ListIterator<MediaContent> iter = catalog.listIterator();
        int count = 0;
        while (iter.hasNext()) {
            MediaContent media = iter.next();
            if (media.getTitle().equals(oldTitle)) {
                media.setTitle(newTitle);
                count++;
            }
        }
        return count; // заглушка
    }

    public void removeRange(int fromIndex, int toIndex) {
        if (fromIndex >= 0 && toIndex <= catalog.size() && fromIndex < toIndex) {
            catalog.subList(fromIndex, toIndex).clear();
            totalItems -= (toIndex - fromIndex);
        }
    }

    // Извлечение диапазона (с удалением из каталога)
    public List<MediaContent> extractRange(int fromIndex, int toIndex) {
        if (fromIndex >= 0 && toIndex <= catalog.size()) {
            List<MediaContent> extracted = new ArrayList<>(catalog.subList(fromIndex, toIndex));
            catalog.subList(fromIndex, toIndex).clear();
            totalItems -= (toIndex - fromIndex);
            return extracted;
        }
        return new ArrayList<>();
    }

    // Пометить диапазон как просмотренный
    public void markRangeAsWatched(int fromIndex, int toIndex) {
        for (MediaContent media : catalog.subList(fromIndex, toIndex)) {
            media.setCompleted(true);
            media.setDateWatched(LocalDate.now());
        }
    }
}





