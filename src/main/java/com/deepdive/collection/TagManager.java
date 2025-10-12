package com.deepdive.collection;

import com.deepdive.model.MediaContent;

import java.util.*;

public class TagManager {
    private MediaCatalog catalog;

    public TagManager(MediaCatalog catalog) {
        this.catalog = catalog;
    }

    public Set<MediaContent> findByTag(String tag) {
        if (tag == null) {
            throw new IllegalArgumentException("tag не может быть null");
        }
        Set<MediaContent> result = new HashSet<>();
        for (MediaContent content : catalog.getAllMedia()) {
            if (content.hasTag(tag)) {
                result.add(content);
            }
        }
        return result;
    }

    public Set<String> getAllTags() {
        Set<String> result = new HashSet<>();
        for (MediaContent content : catalog.getAllMedia()) {
            result.addAll(content.getTags());
        }
        return result;
    }
}
