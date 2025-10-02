package com.deepdive.collection;

import com.deepdive.model.Video;

import java.util.*;

/**
 * Очередь просмотра видео (FIFO - First In First Out).
 * <p>
 * Реальное применение:
 * - YouTube playlist queue
 * - Netflix "Мой список"
 * - Очередь загрузок в приложениях
 * <p>
 * ПРИМЕЧАНИЕ: В production для многопоточной обработки
 * используется BlockingQueue (изучим в пункте 11 - Многопоточность)
 */

public class WatchQueue {
    private Queue<Video> queue;
    private int totalWatched;

    // Конструктор
    public WatchQueue() {
        this.queue = new LinkedList<>();
        totalWatched = 0;
    }

    // === ОСНОВНЫЕ ОПЕРАЦИИ QUEUE ===

    /**
     * Добавляет видео в конец очереди.
     *
     * @param video видео для добавления
     * @return true если добавлено, false если video == null
     */
    public boolean addToQueue(Video video) {
        if (video != null) {
            queue.offer(video);
            return true;
        }
        return false;
    }

    /**
     * Извлекает и удаляет следующее видео из очереди.
     *
     * @return следующее видео или null если очередь пуста
     */
    public Video getNextVideo() {
        Video video = queue.poll();
        if (video != null) {
            totalWatched++;
        }
        return video;
    }


    /**
     * Показывает следующее видео БЕЗ удаления из очереди.
     *
     * @return следующее видео или null если очередь пуста
     */
    public Video peekNextVideo() {
        return queue.peek();
    }

    // === ДОПОЛНИТЕЛЬНЫЕ ОПЕРАЦИИ ===

    /**
     * Добавляет срочное видео в НАЧАЛО очереди.
     *
     * @param video срочное видео
     */
    public void addUrgentVideo(Video video) {
        if (video != null) {
            ((LinkedList<Video>) queue).offerFirst(video);
        }
    }

    /**
     * Очищает всю очередь.
     */
    public void clearQueue() {
        queue.clear();
    }

    /**
     * Удаляет конкретное видео из очереди.
     *
     * @param video видео для удаления
     * @return true если удалено, false если не найдено
     */
    public boolean removeVideo(Video video) {
        return queue.remove(video);
    }

    // === ИНФОРМАЦИЯ О ОЧЕРЕДИ ===

    /**
     * Проверяет, есть ли видео в очереди.
     *
     * @return true если очередь НЕ пуста
     */
    public boolean hasVideos() {
        return !queue.isEmpty();
    }

    /**
     * Возвращает количество видео в очереди.
     *
     * @return размер очереди
     */
    public int getQueueSize() {
        return queue.size();
    }

    /**
     * Возвращает общее количество просмотренных видео.
     *
     * @return количество просмотренных
     */
    public int getTotalWatched() {
        return totalWatched;
    }

    /**
     * Возвращает список всех видео в очереди (для просмотра).
     *
     * @return копия списка видео
     */
    public List<Video> getAllVideos() {
        return new ArrayList<>(queue); // заглушка
    }

    /**
     * Выводит информацию о очереди.
     */
    public void printQueueInfo() {
        System.out.println("=== Очередь просмотра ===");
        System.out.println("В очереди: " + getQueueSize() + " видео");
        System.out.println("Просмотрено всего: " + totalWatched);

        if (!queue.isEmpty()) {
            System.out.println("Следующее: " + peekNextVideo().getTitle());
        }
    }
}

