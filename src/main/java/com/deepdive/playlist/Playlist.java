package com.deepdive.playlist;

import com.deepdive.model.MediaContent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
