package com.deepdive.history;

import com.deepdive.model.Video;

import java.util.LinkedList;
import java.util.*;

public class ViewingHistory {
    private final static int MAX_HISTORY = 50;
    private Deque<Video> history;

    public ViewingHistory() {
        this.history = new LinkedList<>();
    }

    public void add(Video video) {
        if (video == null) {
            throw new IllegalArgumentException("video не может быть null");
        }
        if (history.size() >= MAX_HISTORY) {
            history.removeLast();
        }
        history.addFirst(video);
    }

    public Video getLastWatched() {
        return history.peekFirst();
    }


    public List<Video> getAll() {
        return List.copyOf(history);
    }

    public int size() {
        return history.size();
    }

    public void clear() {
        history.clear();
    }
}
