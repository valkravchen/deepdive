package com.deepdive.test;

import com.deepdive.collection.PlaybackSession;
import com.deepdive.model.Video;
import com.deepdive.model.enums.MediaType;

public class TestPlaybackSession {
    public static void main(String[] args) {
        System.out.println("=== Тест PlaybackSession ===\n");
        PlaybackSession session = new PlaybackSession("Природа");
        Video video1 = new Video("Планета Земля", 2006, MediaType.DOCUMENTARY_SERIES);
        Video video2 = new Video("Голубая планета", 2001, MediaType.DOCUMENTARY_SERIES);
        Video video3 = new Video("Космос", 1980, MediaType.DOCUMENTARY_SERIES);
        Video video4 = new Video("Океаны", 2009, MediaType.DOCUMENTARY);
        Video video5 = new Video("Африка", 2013, MediaType.DOCUMENTARY_SERIES);
        System.out.println("=== Добавление треков ===");
        System.out.println("Добавлен video1: " + session.addVideo(video1));
        System.out.println("Добавлен video2: " + session.addVideo(video2));
        System.out.println("Добавлен video3: " + session.addVideo(video3));
        System.out.println("Размер плейлиста: " + session.getPlaylistSize());
        System.out.println();
        session.printSessionInfo();
        System.out.println();
        System.out.println("=== Навигация ===");
        Video current = session.getCurrentVideo();
        System.out.println("Текущий трек: " + (current != null ? current.getTitle() : "null"));
        System.out.println("Есть следующий: " + session.hasNext());
        System.out.println("Есть предыдущий: " + session.hasPrevious());
        System.out.println("\nПереход next():");
        session.next();
        System.out.println("Текущий: " + session.getCurrentVideo().getTitle());
        session.next();
        System.out.println("После еще одного next(): " + session.getCurrentVideo().getTitle());
        System.out.println("\nПереход previous():");
        session.previous();
        System.out.println("Текущий: " + session.getCurrentVideo().getTitle());
        System.out.println();
        System.out.println("=== Добавление следующим треком ===");
        session.addNext(video4);
        System.out.println("Добавлен video4 следующим");
        session.next();
        System.out.println("После next(): " + session.getCurrentVideo().getTitle());
        System.out.println();
        System.out.println("=== Переход к позиции ===");
        System.out.println("jumpTo(0): " + session.jumpTo(0));
        System.out.println("Текущий: " + session.getCurrentVideo().getTitle());
        System.out.println();
        System.out.println("=== Переход в конец ===");
        session.toEnd();
        System.out.println("Текущий: " + session.getCurrentVideo().getTitle());
        System.out.println("Позиция: " + (session.getCurrentIndex() + 1) + "/" + session.getPlaylistSize());
        System.out.println();
        System.out.println("=== Удаление текущего трека ===");
        Video removed = session.removeCurrentAndNext();
        System.out.println("Удален: " + (removed != null ? removed.getTitle() : "null"));
        System.out.println("Новый текущий: " +
                (session.getCurrentVideo() != null ? session.getCurrentVideo().getTitle() : "null"));
        System.out.println("Размер: " + session.getPlaylistSize());
        System.out.println();
        System.out.println("=== Перемешивание ===");
        session.addVideo(video5);
        System.out.println("Размер перед shuffle: " + session.getPlaylistSize());
        session.toStart();
        String beforeShuffle = session.getCurrentVideo().getTitle();
        System.out.println("Первый трек до shuffle: " + beforeShuffle);
        session.shuffle();
        System.out.println("Shuffle выполнен");
        System.out.println("Shuffle активен: " + session.isShuffled());
        System.out.println("Текущий трек после shuffle: " + session.getCurrentVideo().getTitle());
        System.out.println();
        System.out.println("=== Очистка плейлиста ===");
        session.clear();
        System.out.println("После clear():");
        System.out.println("Размер: " + session.getPlaylistSize());
        System.out.println("Пуст: " + session.isEmpty());
        System.out.println("Текущий индекс: " + session.getCurrentIndex());
        System.out.println("\n✓ Тест PlaybackSession завершен!");
    }
}

