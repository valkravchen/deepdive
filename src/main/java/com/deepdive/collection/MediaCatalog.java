package com.deepdive.collection;

import com.deepdive.model.MediaContent;
import com.deepdive.model.enums.MediaType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaCatalog {
    private List<MediaContent> items;

    public MediaCatalog() {
        this.items = new ArrayList<>();
    }

    public boolean addMedia(MediaContent media) {
        if (media == null) {
            return false;
        }
        return items.add(media);
    }

    public boolean removeMedia(String id) {
        for (MediaContent content : items) {
            if (content.getId().equals(id)) {
                items.remove(content);
                return true;
            }
        }
        return false;
    }

    public MediaContent findById(String id) {
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
        return items; // прямая ссылка?
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
        List<MediaContent> result = new ArrayList<>();
        for (MediaContent content : items) {
            if (content.getType() == type) {
                result.add(content);
            }
        }
        return result;
    }

    public List<MediaContent> findByTitle(String titlePart) {
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
}

