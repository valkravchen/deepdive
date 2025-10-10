package com.deepdive.queue;

import com.deepdive.model.Video;

import java.util.LinkedList;
import java.util.Queue;

public class WatchQueue {
    private Queue<Video> queue;


    public WatchQueue() {
        this.queue = new LinkedList<>();
    }

    public boolean addToQueue(Video video) {
        if (video == null) {
            throw new IllegalArgumentException("video не может быть null");
        }
        return queue.offer(video);
    }

    public Video takeNext() {
        return queue.poll();
    }

    public Video peekNext() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }


    public int size() {
        return queue.size();
    }

    public void clear() {
        queue.clear();
    }
}
