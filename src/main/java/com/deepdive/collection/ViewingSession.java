package com.deepdive.collection;

import com.deepdive.model.MediaContent;

import java.util.List;

public class ViewingSession {
    List<MediaContent> items;
    int currentIndex;

    public ViewingSession(MediaCatalog catalog) {
        this.currentIndex = -1;
        this.items = List.copyOf(catalog.getAllMedia());
    }

    public MediaContent next() {
        if (currentIndex == items.size() - 1) {
            return null;
        }
        currentIndex++;
        return items.get(currentIndex);
    }

    public MediaContent previous() {
        if (currentIndex == 0) {
            return null;
        }
        currentIndex--;
        return items.get(currentIndex);
    }

    public boolean hasNext() {
        return getCurrentIndex() < getTotal() - 1;
    }

    public boolean hasPrevious() {
        return getCurrentIndex() > 0;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getTotal() {
        return items.size();
    }
}
