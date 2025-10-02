package com.deepdive.test;

import com.deepdive.collection.WatchQueue;
import com.deepdive.model.Video;
import com.deepdive.model.enums.MediaType;

public class TestWatchQueue {
    public static void main(String[] args) {
        WatchQueue watchQueue = new WatchQueue();
        Video video1 = new Video("Планета Земля", 2006, MediaType.DOCUMENTARY_SERIES);
        Video video2 = new Video("Голубая планета", 2001, MediaType.DOCUMENTARY_SERIES);
        Video video3 = new Video("Космос", 1980, MediaType.DOCUMENTARY_SERIES);
        Video video4 = new Video("Океаны", 2009, MediaType.DOCUMENTARY);
        System.out.println("=== Добавление видео ===");
        System.out.println("Добавлено video1: " + watchQueue.addToQueue(video1));
        System.out.println("Добавлено video2: " + watchQueue.addToQueue(video2));
        System.out.println("Добавлено video3: " + watchQueue.addToQueue(video3));
        System.out.println("Добавлено null: " + watchQueue.addToQueue(null));
        System.out.println("Размер очереди: " + watchQueue.getQueueSize());
        System.out.println("\n=== Просмотр следующего ===");
        Video next = watchQueue.peekNextVideo();
        System.out.println("Следующее видео: " + (next != null ? next.getTitle() : "null"));
        System.out.println("Размер после peek: " + watchQueue.getQueueSize());
        System.out.println("\n=== Просмотр видео ===");
        Video watched1 = watchQueue.getNextVideo();
        System.out.println("Просмотрено: " + (watched1 != null ? watched1.getTitle() : "null"));
        System.out.println("Размер после просмотра: " + watchQueue.getQueueSize());
        System.out.println("Всего просмотрено: " + watchQueue.getTotalWatched());
        Video watched2 = watchQueue.getNextVideo();
        System.out.println("Просмотрено: " + (watched2 != null ? watched2.getTitle() : "null"));
        System.out.println("Всего просмотрено: " + watchQueue.getTotalWatched());
        System.out.println("\n=== Добавление срочного видео ===");
        watchQueue.addUrgentVideo(video4);
        System.out.println("Добавлено срочное: " + video4.getTitle());
        Video nextNow = watchQueue.peekNextVideo();
        System.out.println("Теперь следующее: " + (nextNow != null ? nextNow.getTitle() : "null"));
        System.out.println("\n=== Информация ===");
        watchQueue.printQueueInfo();
        System.out.println("\n=== Удаление видео ===");
        boolean removed = watchQueue.removeVideo(video3);
        System.out.println("Удалено video3: " + removed);
        System.out.println("Размер после удаления: " + watchQueue.getQueueSize());
        System.out.println("\n=== Просмотр всех оставшихся ===");
        while (watchQueue.hasVideos()) {
            Video v = watchQueue.getNextVideo();
            System.out.println("Просмотрено: " + v.getTitle());
        }
        System.out.println("Есть еще видео: " + watchQueue.hasVideos());
        System.out.println("Всего просмотрено: " + watchQueue.getTotalWatched());
        System.out.println("\n=== Тест пустой очереди ===");
        Video empty = watchQueue.getNextVideo();
        System.out.println("Из пустой очереди: " + (empty != null ? empty.getTitle() : "null"));
        System.out.println("\n✓ Тест WatchQueue завершен!");
    }
}

