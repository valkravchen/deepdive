package com.deepdive.playlist;

import com.deepdive.model.MediaContent;
import com.deepdive.model.enums.Genre;

import java.util.*;

public class Playlist {
    private String name;
    private List<MediaContent> playlist;

    public Playlist(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name не может быть null или пустым");
        }
        this.name = name;
        this.playlist = new ArrayList<>();
    }

    public boolean add(MediaContent content) {
        if (content == null) {
            throw new IllegalArgumentException("content не может быть null");
        }
        return playlist.add(content);
    }

    public void shuffle() {
        Collections.shuffle(playlist);
    }

    public void reverse() {
        Collections.reverse(playlist);
    }

    public void rotate(int distance) {
        Collections.rotate(playlist, distance);
    }

    public List<MediaContent> getItems() {
        return new ArrayList<>(playlist);
    }

    public int size() {
        return playlist.size();
    }

    public String getName() {
        return name;
    }

    public void printPlaylist() {
        System.out.println("=== Плейлист: " + name + " ===");
        for (int index = 0; index < playlist.size(); index++) {
            MediaContent content = playlist.get(index);
            System.out.println((index + 1) + ". " + content.getTitle() + " (" + content.getYear() + ")");
        }
        System.out.println("Всего: " + playlist.size() + " элементов");
    }

    public int importFrom(List<MediaContent> source) {
        if (source == null) {
            throw new IllegalArgumentException("source не может быть null");
        }
        int sizeBefore = playlist.size();
        playlist.addAll(source);
        return playlist.size() - sizeBefore;
    }

    public int removeCompleted() {
        List<MediaContent> completed = new ArrayList<>();
        for (MediaContent mediaContent : playlist) {
            if (mediaContent.isCompleted()) {
                completed.add(mediaContent);
            }
        }
        playlist.removeAll(completed);
        return completed.size();
    }

    public int keepOnlyGenres(Set<Genre> genresToKeep) {
        if (genresToKeep == null) {
            throw new IllegalArgumentException("genresToKeep не может быть null");
        }
        int removed = 0;
        List<MediaContent> toRemove = new ArrayList<>();
        for (MediaContent content : playlist) {
            if (Collections.disjoint(content.getGenres(), genresToKeep)) {
                toRemove.add(content);
                removed++;
            }
        }

        playlist.removeAll(toRemove);
        return removed;
    }

    public int mergeWith(Playlist other) {
        if (other == null) {
            throw new IllegalArgumentException("other не может быть null");
        }
        int beforeSize = playlist.size();
        playlist.addAll(other.playlist);
        return (playlist.size() - beforeSize);
    }

    public List<MediaContent> findCommonWith(Playlist other) {
        List<MediaContent> copy = new ArrayList<>(playlist);
        copy.retainAll(other.playlist);
        return copy;
    }
}