package com.deepdive.collection;

import com.deepdive.model.MediaContent;
import com.deepdive.model.enums.MediaType;

import java.util.*;

public class MediaCatalog {
    private List<MediaContent> items;

    public MediaCatalog() {
        this.items = new ArrayList<>();
    }

    public boolean addMedia(MediaContent media) {
        if (media == null) {
            throw new IllegalArgumentException("media не может быть null");
        }
        return items.add(media);
    }

    public boolean removeMedia(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id не может быть null");
        }
        for (MediaContent content : items) {
            if (content.getId().equals(id)) {
                items.remove(content);
                return true;
            }
        }
        return false;
    }

    public MediaContent findById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id не может быть null");
        }
        for (MediaContent content : items) {
            if (content.getId().equals(id)) {
                return content;
            }
        }
        return null;
    }

    public int getTotalItems() {
        return items.size();
    }

    public List<MediaContent> getAllMedia() {
        return new ArrayList<>(items);
    }

    public List<MediaContent> findByYear(int year) {
        List<MediaContent> result = new ArrayList<>();
        for (MediaContent content : items) {
            if (content.getYear() == year) {
                result.add(content);
            }
        }
        return result;
    }

    public List<MediaContent> findByType(MediaType type) {
        if (type == null) {
            throw new IllegalArgumentException("type не может быть null");
        }
        List<MediaContent> result = new ArrayList<>();
        for (MediaContent content : items) {
            if (content.getType() == type) {
                result.add(content);
            }
        }
        return result;
    }

    public List<MediaContent> findByTitle(String titlePart) {
        if (titlePart == null) {
            throw new IllegalArgumentException("titlePart не может быть null");
        }
        List<MediaContent> result = new ArrayList<>();
        for (MediaContent content : items) {
            if (content.getTitle().toLowerCase().contains(titlePart.toLowerCase())) {
                result.add(content);
            }
        }
        return result;
    }

    public int removeOldContent(int olderThan) {
        int removed = 0;
        Iterator<MediaContent> iterator = items.iterator();
        while (iterator.hasNext()) {
            MediaContent item = iterator.next();
            if (item.getYear() < olderThan) {
                iterator.remove();
                removed++;
            }
        }
        return removed;
    }

    public MediaContent getByPosition(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("index вне допустимых границ");
        }
        return items.get(index);
    }

    public MediaContent replaceAt(int index, MediaContent newMedia) {
        if (newMedia == null) {
            throw new IllegalArgumentException("newMedia не может быть null");
        }
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("index вне допустимых границ");
        }

        return items.set(index, newMedia);
    }

    public void insertAt(int index, MediaContent media) {
        if (media == null) {
            throw new IllegalArgumentException("media не может быть null");
        }

        if (index < 0 || index > items.size()) {
            throw new IndexOutOfBoundsException("index вне допустимых границ");
        }
        items.add(index, media);
    }

    public MediaContent removeAt(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("index вне допустимых границ");
        }
        return items.remove(index);
    }
}

