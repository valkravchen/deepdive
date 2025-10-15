package com.deepdive.collection;

import com.deepdive.model.enums.Genre;

import java.util.EnumSet;
import java.util.Set;

public class GenreFilter {
    public EnumSet<Genre> selectedGenres = EnumSet.noneOf(Genre.class);

    public boolean addGenre(Genre genre) {
        if (genre == null) {
            return false;
        }
        return selectedGenres.add(genre);
    }

    public boolean removeGenre(Genre genre) {
        if (genre == null) {
            return false;
        }
        return selectedGenres.remove(genre);
    }

    public boolean hasGenre(Genre genre) {
        if (genre == null) {
            return false;
        }
        return selectedGenres.contains(genre);
    }

    public Set<Genre> getSelectedGenres() {
        return EnumSet.copyOf(selectedGenres);
    }

    public void clear() {
        selectedGenres.clear();
    }

    public void selectAll() {
        EnumSet.allOf(Genre.class);
    }

    // TODO: Создай метод union (объединение)
    // - Принимает GenreFilter other
    // - Возвращает GenreFilter с объединением жанров
    // - Создай новый фильтр, добавь жанры из обоих

    public GenreFilter union(GenreFilter other) {
        EnumSet<Genre> union = EnumSet.copyOf(selectedGenres);

        union.addAll(other.getSelectedGenres());
    }
}

// TODO: Создай метод intersect (пересечение)
// - Принимает GenreFilter other
// - Возвращает GenreFilter с общими жанрами
// - Используй retainAll для пересечения


// TODO: Создай метод complement (дополнение)
// - Возвращает GenreFilter с НЕвыбранными жанрами
// - Используй EnumSet.complementOf()
//    public GenreFilter complement() {
//
//    }

}
