package com.deepdive.collection;

import com.deepdive.model.Video;

import java.util.*;

/**
 * Сессия воспроизведения плейлиста с навигацией.
 * <p>
 * Реальное применение:
 * - Music players (Spotify, Apple Music)
 * - Video players (VLC, YouTube)
 * - Podcast players
 * <p>
 * ПРИМЕЧАНИЕ: LinkedList хорош для добавления/удаления текущего трека.
 * В реальных плеерах часто используют более сложные структуры
 * с индексами для быстрого доступа.
 */

public class PlaybackSession {
    private LinkedList<Video> playlist;
    private int currentIndex;
    private String sessionName;
    private boolean isShuffled;

    public PlaybackSession(String sessionName) {
        this.playlist = new LinkedList<>();
        this.currentIndex = -1;
        this.sessionName = sessionName;
        this.isShuffled = false;
    }

    // === УПРАВЛЕНИЕ ПЛЕЙЛИСТОМ ===

    /**
     * Добавляет видео в конец плейлиста.
     *
     * @param video видео для добавления
     * @return true если добавлено
     */
    public boolean addVideo(Video video) {
        if (video != null) {
            playlist.add(video);
            if (currentIndex == -1) {
                currentIndex = 0;
            }
            return true;
        }
        return false;
    }

    /**
     * Добавляет видео после текущего трека (следующим в очереди).
     *
     * @param video видео для добавления
     */
    public void addNext(Video video) {
        if (video != null) {
            if (currentIndex == -1) {
                playlist.addFirst(video);
            } else {
                playlist.add(currentIndex + 1, video);
            }
        }
    }

    /**
     * Удаляет видео из плейлиста по индексу.
     *
     * @param index индекс видео
     * @return удаленное видео или null
     */
    public Video removeVideo(int index) {
        if (index < 0 || index >= playlist.size()) {
            return null;
        }
        Video removed = playlist.remove(index);
        if (index < currentIndex) {
            currentIndex--;
        } else if (index == currentIndex) {
            if (currentIndex >= playlist.size()) {
                currentIndex--;
            }
        }
        if (playlist.isEmpty()) {
            currentIndex = -1;
        }
        return removed;
    }

    /**
     * Удаляет текущий трек и переходит к следующему.
     *
     * @return удаленное видео или null
     */
    public Video removeCurrentAndNext() {
        if (currentIndex < 0 || currentIndex >= playlist.size()) {
            return null;
        }
        Video removed = playlist.remove(currentIndex);
        if (currentIndex >= playlist.size() && currentIndex > 0) {
            currentIndex--;
        }
        if (playlist.isEmpty()) {
            currentIndex = -1;
        }
        return removed;
    }

    /**
     * Очищает весь плейлист.
     */
    public void clear() {
        playlist.clear();
        currentIndex = -1;
        isShuffled = false;
    }

// === НАВИГАЦИЯ ===

    /**
     * Переход к следующему треку.
     *
     * @return true если переход выполнен
     */
    public boolean next() {
        if (currentIndex < playlist.size() - 1) {
            currentIndex++;
            return true;
        }
        return false; // заглушка
    }

    /**
     * Переход к предыдущему треку.
     *
     * @return true если переход выполнен
     */
    public boolean previous() {
        if (currentIndex > 0) {
            currentIndex--;
            return true;
        }
        return false; // заглушка
    }

    /**
     * Переход к конкретной позиции.
     *
     * @param index индекс трека
     * @return true если переход выполнен
     */
    public boolean jumpTo(int index) {
        if (index >= 0 && index < playlist.size()) {
            currentIndex = index;
            return true;
        }
        return false; // заглушка
    }

    /**
     * Переход в начало плейлиста.
     */
    public void toStart() {
        if (!playlist.isEmpty()) {
            currentIndex = 0;
        }
    }

    /**
     * Переход в конец плейлиста.
     */
    public void toEnd() {
        if (!playlist.isEmpty()) {
            currentIndex = playlist.size() - 1;
        }
    }

// === ПОЛУЧЕНИЕ ИНФОРМАЦИИ ===

    /**
     * Возвращает текущий трек.
     *
     * @return текущее видео или null
     */
    public Video getCurrentVideo() {
        if (currentIndex >= 0 && currentIndex < playlist.size()) {
            return playlist.get(currentIndex);
        }
        return null; // заглушка
    }

    /**
     * Проверяет, есть ли следующий трек.
     *
     * @return true если есть следующий
     */
    public boolean hasNext() {
        return currentIndex < playlist.size() - 1;
    }

    /**
     * Проверяет, есть ли предыдущий трек.
     *
     * @return true если есть предыдущий
     */
    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    /**
     * Возвращает текущую позицию.
     *
     * @return индекс текущего трека
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * Возвращает размер плейлиста.
     *
     * @return количество треков
     */
    public int getPlaylistSize() {
        return playlist.size();
    }

    /**
     * Проверяет, пуст ли плейлист.
     *
     * @return true если пуст
     */
    public boolean isEmpty() {
        return playlist.isEmpty();
    }

// === СПЕЦИАЛЬНЫЕ ФУНКЦИИ ===

    /**
     * Перемешивает плейлист (shuffle).
     */
    public void shuffle() {
        if (!playlist.isEmpty()) {
            Video current = getCurrentVideo();
            Collections.shuffle(playlist);
            currentIndex = playlist.indexOf(current);
            isShuffled = true;
        }
    }

    /**
     * Проверяет, перемешан ли плейлист.
     *
     * @return true если shuffle активен
     */
    public boolean isShuffled() {
        return isShuffled;
    }

    /**
     * Возвращает копию плейлиста.
     *
     * @return список видео
     */
    public List<Video> getPlaylist() {
        return new ArrayList<>(playlist); // заглушка
    }

    /**
     * Выводит информацию о сессии.
     */
    public void printSessionInfo() {
        System.out.println("=== Сессия: " + sessionName + " ===");
        System.out.println("Треков в плейлисте: " + getPlaylistSize());

        if (!isEmpty()) {
            System.out.println("Текущий трек: " + (currentIndex + 1) + "/" + getPlaylistSize());
            Video current = getCurrentVideo();
            if (current != null) {
                System.out.println("Воспроизводится: " + current.getTitle());
            }
        }

        System.out.println("Shuffle: " + (isShuffled ? "ВКЛ" : "ВЫКЛ"));
    }
}

