package com.deepdive.collection;

import com.deepdive.model.MediaContent;

import java.util.HashSet;
import java.util.Set;

public class CollectionComparator {
    public Set<MediaContent> findCommon(Set<MediaContent> collection1, Set<MediaContent> collection2) {
        if (collection1 == null || collection2 == null) {
            return new HashSet<>();
        }
        Set<MediaContent> copy = new HashSet<>(collection1);
        copy.retainAll(collection2);
        return copy;
    }

    public Set<MediaContent> findUnique(Set<MediaContent> collection1, Set<MediaContent> collection2) {
        if (collection1 == null || collection2 == null) {
            return new HashSet<>();
        }
        Set<MediaContent> copy = new HashSet<>(collection1);
        copy.removeAll(collection2);
        return copy;
    }

    public Set<MediaContent> findAll(Set<MediaContent> collection1, Set<MediaContent> collection2) {
        if (collection1 == null || collection2 == null) {
            return new HashSet<>();
        }
        Set<MediaContent> copy = new HashSet<>(collection1);
        copy.addAll(collection2);
        return copy;
    }

    public Set<MediaContent> getRecommendations(Set<MediaContent> userCollection, Set<MediaContent> otherCollection) {
        if (userCollection == null || otherCollection == null) {
            return new HashSet<>();
        }
        Set<MediaContent> copy = new HashSet<>(otherCollection);
        copy.removeAll(userCollection);
        return copy;
    }
}
