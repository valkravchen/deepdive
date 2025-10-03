package com.deepdive.collection;

import com.deepdive.model.Video;

import java.time.LocalDateTime;
import java.util.*;

/**
 * История просмотров с автоматическим ограничением размера.
 * Использует паттерн LRU (Least Recently Used).
 * <p>
 * Реальное применение:
 * - YouTube история просмотра
 * - Redis хранение последних событий
 * - Session management в веб-приложениях
 * <p>
 * ПРИМЕЧАНИЕ: В production часто используется специализированный
 * LRU cache (Caffeine, Guava Cache, Redis), но принцип тот же.
 */

public class ViewingHistory {
    private static final int DEFAULT_MAX_SIZE = 100;
    private Deque<HistoryEntry> history;
    private int maxSize;

    /**
     * Запись в истории просмотров.
     */
    public static class HistoryEntry {
        private Video video;
        private LocalDateTime watchedAt;
        private int watchedMinutes;

        public HistoryEntry(Video video, int watchedMinutes) {
            this.video = video;
            this.watchedMinutes = watchedMinutes;
            this.watchedAt = LocalDateTime.now();
        }

        public Video getVideo() {
            return video;
        }

        public LocalDateTime getWatchedAt() {
            return watchedAt;
        }

        public int getWatchedMinutes() {
            return watchedMinutes;
        }

        @Override
        public String toString() {
            return video.getTitle() + " - " + watchedMinutes + " мин (" +
                    watchedAt.toLocalDate() + ")";
        }
    }

    // Конструкторы
    public ViewingHistory() {
        this.history = new LinkedList<>();
        this.maxSize = DEFAULT_MAX_SIZE;
    }

    public ViewingHistory(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.history = new LinkedList<>();
        this.maxSize = maxSize;
    }


    // === ОСНОВНЫЕ ОПЕРАЦИИ ===

    /**
     * Добавляет запись о просмотре в историю.
     * Новая запись добавляется в НАЧАЛО (самая свежая).
     * Если размер превышен, удаляется самая старая запись.
     *
     * @param video          просмотренное видео
     * @param watchedMinutes сколько минут просмотрено
     */
    public void addToHistory(Video video, int watchedMinutes) {
        if (video != null) {
            HistoryEntry entry = new HistoryEntry(video, watchedMinutes);
            history.addFirst(entry);
            if (history.size() > maxSize) {
                history.removeLast();
            }
        }
    }

    /**
     * Возвращает последние N записей из истории.
     *
     * @param count количество записей
     * @return список последних записей
     */
    public List<HistoryEntry> getRecent(int count) {
        List<HistoryEntry> result = new ArrayList<>();
        int added = 0;

        for (HistoryEntry entry : history) {
            if (added >= count) break;
            result.add(entry);
            added++;
        }
        return result;
    }

    /**
     * Возвращает последнюю просмотренную запись.
     *
     * @return последняя запись или null
     */
    public HistoryEntry getLastWatched() {
        return history.peekFirst();
    }

    /**
     * Очищает всю историю.
     */
    public void clearHistory() {
        history.clear();
    }

    // === ПОИСК В ИСТОРИИ ===

    /**
     * Ищет все просмотры конкретного видео в истории.
     *
     * @param videoTitle название видео
     * @return список записей с этим видео
     */
    public List<HistoryEntry> findByTitle(String videoTitle) {
        List<HistoryEntry> result = new ArrayList<>();
        for (HistoryEntry entry : history) {
            if (entry.getVideo().getTitle().equals(videoTitle)) {
                result.add(entry);
            }
        }
        return result;
    }

    /**
     * Проверяет, было ли видео в истории просмотров.
     *
     * @param video видео для проверки
     * @return true если было просмотрено
     */
    public boolean wasWatched(Video video) {
        for (HistoryEntry entry : history) {
            if (entry.getVideo().equals(video)) {
                return true;
            }
        }
        return false; // заглушка
    }

    // === УДАЛЕНИЕ ИЗ ИСТОРИИ ===

    /**
     * Удаляет все записи о конкретном видео из истории.
     *
     * @param video видео для удаления
     * @return количество удаленных записей
     */
    public int removeVideo(Video video) {
        int count = 0;
        Iterator<HistoryEntry> iter = history.iterator();
        while (iter.hasNext()) {
            if (iter.next().getVideo().equals(video)) {
                iter.remove();
                count++;
            }
        }
        return count;
    }

    /**
     * Удаляет записи старше указанной даты.
     *
     * @param date граничная дата
     * @return количество удаленных записей
     */
    public int removeOlderThan(LocalDateTime date) {
        int count = 0;
        Iterator<HistoryEntry> iter = history.iterator();
        while (iter.hasNext()) {
            if (iter.next().getWatchedAt().isBefore(date)) {
                iter.remove();
                count++;
            }
        }
        return count; // заглушка
    }

    // === СТАТИСТИКА ===

    /**
     * Возвращает общее количество просмотренных минут.
     *
     * @return сумма всех watchedMinutes
     */
    public int getTotalMinutesWatched() {
        int total = 0;
        for (HistoryEntry entry : history) {
            total += entry.getWatchedMinutes();

        }
        return total;
    }

    /**
     * Возвращает количество записей в истории.
     *
     * @return размер истории
     */
    public int getHistorySize() {
        return history.size();
    }

    /**
     * Проверяет, пуста ли история.
     *
     * @return true если пуста
     */
    public boolean isEmpty() {
        return history.isEmpty();
    }

    /**
     * Возвращает максимальный размер истории.
     *
     * @return максимальный размер
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Выводит информацию об истории.
     */
    public void printHistoryInfo() {
        System.out.println("=== История просмотров ===");
        System.out.println("Записей: " + getHistorySize() + " / " + maxSize);
        System.out.println("Всего просмотрено: " + getTotalMinutesWatched() + " минут");

        if (!isEmpty()) {
            System.out.println("Последний просмотр: " + getLastWatched());
        }
    }

    /**
     * Выводит последние N записей.
     */
    public void printRecent(int count) {
        System.out.println("=== Последние " + count + " просмотров ===");
        List<HistoryEntry> recent = getRecent(count);

        for (int i = 0; i < recent.size(); i++) {
            System.out.println((i + 1) + ". " + recent.get(i));
        }
    }
}

