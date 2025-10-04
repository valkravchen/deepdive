package com.deepdive.util;

import com.deepdive.model.MediaContent;
import com.deepdive.model.enums.MediaType;

import java.util.*;

/**
 * Утилитный класс для фильтрации и очистки списков контента.
 * <p>
 * Реальное применение:
 * - Очистка устаревших данных
 * - Удаление дубликатов в базах
 * - Batch-обработка коллекций
 * - Data cleaning pipelines
 * <p>
 * Паттерн: Utility class (как Collections, Arrays, Objects)
 * - Работает с List<MediaContent> напрямую
 * - Все методы static
 * - Приватный конструктор
 * <p>
 * ВАЖНО: Использует Iterator для безопасного удаления во время итерации.
 * Прямое удаление в цикле for-each вызовет ConcurrentModificationException!
 */
public class ContentFilter {

    // Приватный конструктор - нельзя создать экземпляр утилитного класса
    private ContentFilter() {
        throw new AssertionError("Utility class - cannot be instantiated");
    }

    // === УДАЛЕНИЕ ПО УСЛОВИЯМ ===

    /**
     * Удаляет весь контент старше указанного года.
     *
     * @param items     список контента для очистки
     * @param olderThan год (контент ДО этого года будет удален)
     * @return количество удаленных элементов
     */
    public static int removeOldContent(List<MediaContent> items, int olderThan) {
        int removed = 0;
        Iterator<MediaContent> iterator = items.iterator();
        while (iterator.hasNext()) {
            MediaContent content = iterator.next();
            if (content.getYear() < olderThan) {
                iterator.remove();
                removed++;
            }
        }
        return removed; // заглушка
    }

    /**
     * Удаляет весь контент указанного типа.
     *
     * @param items список для очистки
     * @param type  тип контента для удаления
     * @return количество удаленных элементов
     */
    public static int removeByType(List<MediaContent> items, MediaType type) {
        int removed = 0;
        Iterator<MediaContent> iterator = items.iterator();
        while (iterator.hasNext()) {
            MediaContent content = iterator.next();
            if (content.getType() == type) {
                iterator.remove();
                removed++;
            }
        }
        return removed; // заглушка
    }

    /**
     * Удаляет контент с рейтингом ниже указанного.
     *
     * @param items     список контента
     * @param minRating минимальный рейтинг (контент ниже будет удален)
     * @return количество удаленных
     */
    public static int removeLowRated(List<MediaContent> items, double minRating) {
        int removed = 0;
        Iterator<MediaContent> iterator = items.iterator();
        while (iterator.hasNext()) {
            MediaContent content = iterator.next();
            if (content.getRating() != null && content.getRating().getStars() < minRating) {
                iterator.remove();
                removed++;
            }
        }
        return removed; // заглушка
    }

    // === ФИЛЬТРАЦИЯ (БЕЗ УДАЛЕНИЯ) ===

    /**
     * Возвращает список контента определенного типа.
     *
     * @param items исходный список
     * @param type  тип контента
     * @return новый список с контентом указанного типа
     */
    public static List<MediaContent> filterByType(List<MediaContent> items, MediaType type) {
        List<MediaContent> result = new ArrayList<>();
        for (MediaContent content : items) {
            if (content.getType() == type) {
                result.add(content);
            }
        }
        return result;
    }

    /**
     * Возвращает все видео (DOCUMENTARY + DOCUMENTARY_SERIES).
     *
     * @param items исходный список
     * @return список всех видео
     */
    public static List<MediaContent> filterAllVideos(List<MediaContent> items) {
        List<MediaContent> result = new ArrayList<>();
        for (MediaContent content : items) {
            MediaType type = content.getType();
            if (type == MediaType.DOCUMENTARY || type == MediaType.DOCUMENTARY_SERIES) {
                result.add(content);
            }
        }
        return result;
    }

    /**
     * Возвращает все книги (BOOK + AUDIOBOOK).
     *
     * @param items исходный список
     * @return список всех книг
     */
    public static List<MediaContent> filterAllBooks(List<MediaContent> items) {
        List<MediaContent> result = new ArrayList<>();
        for (MediaContent content : items) {
            MediaType type = content.getType();
            if (type == MediaType.BOOK || type == MediaType.AUDIOBOOK) {
                result.add(content);
            }
        }
        return result;
    }

    /**
     * Возвращает контент с рейтингом выше указанного.
     *
     * @param items     исходный список
     * @param minRating минимальный рейтинг
     * @return список контента с рейтингом >= minRating
     */
    public static List<MediaContent> filterByRating(List<MediaContent> items, double minRating) {
        List<MediaContent> result = new ArrayList<>();
        for (MediaContent content : items) {
            if (content.getRating() != null && content.getRating().getStars() >= minRating) {
                result.add(content);
            }
        }
        return result;
    }

    /**
     * Возвращает контент из указанного диапазона лет.
     *
     * @param items    исходный список
     * @param fromYear начальный год (включительно)
     * @param toYear   конечный год (включительно)
     * @return список контента из диапазона
     */
    public static List<MediaContent> filterByYearRange(List<MediaContent> items,
                                                       int fromYear, int toYear) {
        List<MediaContent> result = new ArrayList<>();
        for (MediaContent content : items) {
            if (content.getYear() >= fromYear && content.getYear() <= toYear) {
                result.add(content);
            }
        }
        return result;
    }

    // === СТАТИСТИКА ===

    /**
     * Подсчитывает количество контента каждого типа.
     *
     * @param items список контента
     * @return строка с статистикой
     */
    public static String getTypeStatistics(List<MediaContent> items) {
        int documentaries = 0;
        int series = 0;
        int books = 0;
        int audiobooks = 0;
        for (MediaContent content : items) {
            if (content.getType() == MediaType.DOCUMENTARY) {
                documentaries++;
            } else if (content.getType() == MediaType.DOCUMENTARY_SERIES) {
                series++;
            } else if (content.getType() == MediaType.BOOK) {
                books++;
            } else if (content.getType() == MediaType.AUDIOBOOK) {
                audiobooks++;
            }
        }

        return MediaType.AUDIOBOOK.getDescription() + ": " + audiobooks + "\n"
                + MediaType.BOOK.getDescription() + ": " + books + "\n"
                + MediaType.DOCUMENTARY.getDescription() + ": " + documentaries + "\n"
                + MediaType.DOCUMENTARY_SERIES.getDescription() + ": " + series + "\n"
                ;
    }

    /**
     * Выводит статистику очистки.
     */
    public static void printCleanupStats(int totalBefore, int removed) {
        System.out.println("=== Статистика очистки ===");
        System.out.println("Было элементов: " + totalBefore);
        System.out.println("Удалено: " + removed);
        System.out.println("Осталось: " + (totalBefore - removed));
        if (totalBefore > 0) {
            double percent = (removed * 100.0) / totalBefore;
            System.out.printf("Удалено: %.1f%%%n", percent);
        }
    }
}