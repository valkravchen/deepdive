package com.deepdive.collection;

import com.deepdive.model.MediaContent;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class ContentComparators {
    public static final Comparator<MediaContent> BY_TITLE = new Comparator<MediaContent>() {
        @Override
        public int compare(MediaContent o1, MediaContent o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static final Comparator<MediaContent> BY_YEAR_THEN_TITLE = new Comparator<MediaContent>() {
        @Override
        public int compare(MediaContent o1, MediaContent o2) {
            int yearCompare = Integer.compare(o1.getYear(), o2.getYear());
            if (yearCompare == 0) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
            return yearCompare;
        }
    };

    public static final Comparator<MediaContent> BY_RATING_YEAR_TITLE = new Comparator<MediaContent>() {
        @Override
        public int compare(MediaContent o1, MediaContent o2) {
            int ratingCompare = Double.compare(o2.getRating(), o1.getRating());
            if (ratingCompare != 0) {
                return ratingCompare;
            }
            int yearCompare = Integer.compare(o2.getYear(), o1.getYear());
            if (yearCompare != 0) {
                return yearCompare;
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static TreeSet<MediaContent> createSortedSet(List<MediaContent> items,
                                                        Comparator<MediaContent> comparator) {
        TreeSet<MediaContent> sortedSet = new TreeSet<>(comparator);
        sortedSet.addAll(items);
        return sortedSet;
    }
}
