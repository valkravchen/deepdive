package com.deepdive.util;

import com.deepdive.model.MediaContent;
import com.deepdive.model.enums.MediaType;
import com.deepdive.collection.MediaCatalog;
import java.util.*;

/**
 * Утилитный класс для фильтрации и очистки каталога.
 *
 * Реальное применение:
 * - Очистка устаревших данных
 * - Удаление дубликатов в базах
 * - Batch-обработка коллекций
 * - Data cleaning pipelines
 *
 * Паттерн: Utility class (как Collections, Arrays, Objects)
 *
 * ВАЖНО: Использует Iterator для безопасного удаления во время итерации.
 * Прямое удаление через catalog.remove() в цикле for-each вызовет
 * ConcurrentModificationException!
 */
public class ContentFilter {

    // Приватный конструктор - нельзя создать экземпляр утилитного класса
    private ContentFilter() {
        throw new AssertionError("Utility class - cannot be instantiated");
    }

    // === УДАЛЕНИЕ ПО УСЛОВИЯМ ===

    /**
     * Удаляет весь контент старше указанного года.
     * @param catalog каталог для очистки
     * @param olderThan год (контент ДО этого года будет удален)
     * @return количество удаленных элементов
     */
    public static int removeOldContent(MediaCatalog catalog, int olderThan) {
        int removed = 0;
        List<MediaContent> items = catalog.getAllMedia();
        Iterator<MediaContent> iterator = items.iterator();
        while (iterator.hasNext()) {
            MediaContent content = iterator.next();
            if (content.getYear() < olderThan) {
                iterator.remove();
                removed++;
            }
        }
        return removed;
    }

    /**
     * Удаляет дубликаты - оставляет только первое вхождение каждого контента.
     * @param catalog каталог для очистки
     * @return количество удаленных дубликатов
     */
    public static int removeDuplicates(MediaCatalog catalog) {
        // TODO: Создать Set<MediaContent> seen = new HashSet<>()
        // TODO: Создать счетчик removed = 0
        // TODO: Получить список и создать Iterator
        // TODO: В цикле:
        //       - Получить элемент
        //       - Если !seen.add(element) (значит уже был):
        //         - iter.remove()
        //         - removed++
        // TODO: Вернуть removed

        // ПРИМЕЧАНИЕ: Set.add() возвращает false если элемент уже был.
        // Используем это для определения дубликатов.

        return 0; // заглушка
    }

    /**
     * Удаляет весь контент указанного типа.
     * @param catalog каталог для очистки
     * @param type тип контента для удаления
     * @return количество удаленных элементов
     */
    public static int removeByType(MediaCatalog catalog, MediaType type) {
        // TODO: Реализовать аналогично removeOldContent
        // TODO: Условие: content.getType() == type
        return 0; // заглушка
    }

    /**
     * Удаляет контент с рейтингом ниже указанного.
     * @param catalog каталог
     * @param minRating минимальный рейтинг (контент ниже будет удален)
     * @return количество удаленных
     */
    public static int removeLowRated(MediaCatalog catalog, double minRating) {
        // TODO: Условие: content.getRating() < minRating
        // TODO: Учесть что контент может не иметь рейтинга (rating = 0.0)
        return 0; // заглушка
    }

    // === ФИЛЬТРАЦИЯ (БЕЗ УДАЛЕНИЯ) ===

    /**
     * Возвращает список контента определенного типа.
     * @param catalog каталог
     * @param type тип контента
     * @return новый список с контентом указанного типа
     */
    public static List<MediaContent> filterByType(MediaCatalog catalog, MediaType type) {
        // TODO: Создать результирующий список
        // TODO: Пройти по всем элементам каталога (можно for-each)
        // TODO: Если тип совпадает, добавить в результат
        // TODO: Вернуть результат

        // ПРИМЕЧАНИЕ: Здесь не нужен Iterator, т.к. мы не модифицируем каталог

        return new ArrayList<>(); // заглушка
    }

    /**
     * Возвращает контент с рейтингом выше указанного.
     * @param catalog каталог
     * @param minRating минимальный рейтинг
     * @return список контента с рейтингом >= minRating
     */
    public static List<MediaContent> filterByRating(MediaCatalog catalog, double minRating) {
        // TODO: Аналогично filterByType
        // TODO: Условие: content.getRating() >= minRating
        return new ArrayList<>(); // заглушка
    }

    /**
     * Возвращает контент из указанного диапазона лет.
     * @param catalog каталог
     * @param fromYear начальный год (включительно)
     * @param toYear конечный год (включительно)
     * @return список контента из диапазона
     */
    public static List<MediaContent> filterByYearRange(MediaCatalog catalog,
                                                       int fromYear, int toYear) {
        // TODO: Условие: content.getYear() >= fromYear && content.getYear() <= toYear
        return new ArrayList<>(); // заглушка
    }

    // === СТАТИСТИКА ===

    /**
     * Подсчитывает количество контента каждого типа.
     * @param catalog каталог
     * @return строка с статистикой
     */
    public static String getTypeStatistics(MediaCatalog catalog) {
        // TODO: Создать счетчики для каждого типа (int books = 0, int videos = 0, ...)
        // TODO: Пройти по каталогу и подсчитать
        // TODO: Сформировать строку с результатами
        // TODO: Вернуть строку

        return ""; // заглушка
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
