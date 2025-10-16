package com.deepdive.collection;

import com.deepdive.model.enums.Genre;

import java.util.EnumSet;
import java.util.Set;

public class GenreFilter {
    private EnumSet<Genre> selectedGenres = EnumSet.noneOf(Genre.class);

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
        selectedGenres = EnumSet.allOf(Genre.class);
    }

    public GenreFilter union(GenreFilter other) {
        if (other == null) {
            throw new IllegalArgumentException("other не может быть null");
        }
        GenreFilter result = new GenreFilter();
        result.selectedGenres.addAll(this.selectedGenres);
        result.selectedGenres.addAll(other.selectedGenres);
        return result;
    }

    public GenreFilter intersect(GenreFilter other) {
        if (other == null) {
            throw new IllegalArgumentException("other не может быть null");
        }
        GenreFilter result = new GenreFilter();
        result.selectedGenres.addAll(this.selectedGenres);
        result.selectedGenres.retainAll(other.selectedGenres);
        return result;
    }

    public GenreFilter complement() {
        GenreFilter result = new GenreFilter();
        result.selectedGenres = EnumSet.complementOf(this.selectedGenres);
        return result;
    }

    public int size() {
        return selectedGenres.size();
    }
}
