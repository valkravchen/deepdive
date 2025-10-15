package com.deepdive.collection;

import com.deepdive.model.MediaContent;
import com.deepdive.model.enums.MediaType;
import java.util.LinkedHashSet;
import java.util.*;

public class RatingManager {
    private MediaCatalog catalog;

    public RatingManager(MediaCatalog catalog) {
        this.catalog = catalog;
    }

    public Set<MediaContent> getTopRated(int limit) {
        if (limit <= 0) {
            return new LinkedHashSet<>();
        }
        TreeSet<MediaContent> sorted = new TreeSet<>();
        sorted.addAll(catalog.getAllMedia());
        Set<MediaContent> result = new LinkedHashSet<>();
        int count = 0;
        for (MediaContent content : sorted) {
            if (count >= limit) break;
            result.add(content);
            count++;
        }
        return result;
    }

    public Set<MediaContent> getTopByType(MediaType type, int limit) {
        if (type == null || limit <= 0) {
            return new LinkedHashSet<>();
        }
        TreeSet<MediaContent> sorted = new TreeSet<>();
        for (MediaContent content : catalog.getAllMedia()) {
            if (content.getType() == type) {
                sorted.add(content);
            }
        }
        Set<MediaContent> result = new LinkedHashSet<>();
        int count = 0;
        for (MediaContent content : sorted) {
            if (count >= limit) break;
            result.add(content);
            count++;
        }
        return result;
    }
}
