package com.deepdive.collection;

import com.deepdive.model.MediaContent;
import com.deepdive.model.enums.MediaType;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class YearNavigator {
    private NavigableSet<MediaContent> contentByYear;

    private static class DummyContent extends MediaContent {
        public DummyContent(int year) {
            super("", year, MediaType.DOCUMENTARY);
        }
    }

    public YearNavigator(MediaCatalog catalog) {
        Comparator<MediaContent> byYear = new Comparator<MediaContent>() {
            @Override
            public int compare(MediaContent o1, MediaContent o2) {
                int yearCompare = Integer.compare(o1.getYear(), o2.getYear());
                if (yearCompare == 0 && !o1.getTitle().isEmpty() && o2.getTitle().isEmpty()) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
                return yearCompare;
            }
        };
        this.contentByYear = new TreeSet<>(byYear);
        contentByYear.addAll(catalog.getAllMedia());
    }


    // TODO: Создай метод getContentBetweenYears
    // - Принимает fromYear (включительно) и toYear (не включительно)
    // - Возвращает NavigableSet<MediaContent>
    // - Создай фиктивные объекты для границ
    // - Использует subSet с фиктивными объектами


    public NavigableSet<MediaContent> getContentBefore(int year) {
        MediaContent dummy = new DummyContent(year);
        return contentByYear.tailSet(dummy, true);
    }

    // TODO: Создай метод getContentAfter
    // - Принимает year (включительно)
    // - Возвращает NavigableSet<MediaContent>
    // - Создай фиктивный объект для границы
    // - Использует tailSet


    // TODO: Создай метод getYearRange
    // - Возвращает String "минГод - максГод"
    // - Использует first() и last()
    // - Проверяет что коллекция не пустая

}

