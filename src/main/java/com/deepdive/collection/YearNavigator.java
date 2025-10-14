package com.deepdive.collection;

import com.deepdive.model.MediaContent;

import java.util.NavigableSet;

public class YearNavigator {
    private NavigableSet<MediaContent> contentByYear;

    public YearNavigator(MediaCatalog catalog) {
        this.contentByYear = contentByYear;
    }
// TODO: Создай конструктор
    // - Принимает MediaCatalog
    // - Создает TreeSet с Comparator по годам
    // - Добавляет весь контент из каталога









    // TODO: Создай метод getContentBetweenYears
    // - Принимает fromYear (включительно) и toYear (не включительно)
    // - Возвращает NavigableSet<MediaContent>
    // - Создай фиктивные объекты для границ
    // - Использует subSet с фиктивными объектами









    // TODO: Создай метод getContentBefore
    // - Принимает year (не включительно)
    // - Возвращает NavigableSet<MediaContent>
    // - Создай фиктивный объект для границы
    // - Использует headSet







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

