package com.deepdive.navigation;

import com.deepdive.model.MediaContent;

import java.util.*;

public class NavigationStack {
    private final Deque<MediaContent> deque;

    public NavigationStack() {
        this.deque = new LinkedList<>();
    }

    public void navigateTo(MediaContent content) {
        if (content == null) {
            throw new IllegalArgumentException("content не может быть null");
        }
        deque.push(content);
    }

    public MediaContent goBack() {
        if (!canGoBack()) {
            throw new IllegalArgumentException("Нет страниц для возврата");
        }
        deque.pop();
        return deque.peek();
    }

    public MediaContent getCurrentScreen() {
        return deque.peek();
    }

    public boolean canGoBack() {
        return deque.size() > 1;
    }

    public int getDepth() {
        return deque.size();
    }
}
