package com.deepdive.collection;

import com.deepdive.model.MediaContent;

import java.util.ArrayList;
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
}

