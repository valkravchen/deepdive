package com.deepdive.collection;

import com.deepdive.model.AudioBook;
import com.deepdive.model.Book;
import com.deepdive.model.MediaContent;
import com.deepdive.model.Video;


import java.util.HashSet;
import java.util.Set;

public class AuthorManager {
    private MediaCatalog catalog;

    public AuthorManager(MediaCatalog catalog) {
        this.catalog = catalog;
    }

    public Set<String> getUniqueAuthors() {
        Set<String> author = new HashSet<>();
        for (MediaContent content : catalog.getAllMedia()) {
            if (content instanceof Book book) {
                if (book.getAuthor() != null) {
                    author.add(book.getAuthor());
                }
            } else if (content instanceof AudioBook audioBook) {
                if (audioBook.getAuthor() != null) {
                    author.add(audioBook.getAuthor());
                }
            }
        }
        return author;
    }

    public Set<String> getUniqueDirectors() {
        Set<String> director = new HashSet<>();
        for (MediaContent content : catalog.getAllMedia()) {
            if (content instanceof Video video) {
                if (video.getDirector() != null) {
                    director.add(video.getDirector());
                }
            }
        }
        return director;
    }

    public Set<String> getUniqueNarrators() {
        Set<String> narrator = new HashSet<>();
        for (MediaContent content : catalog.getAllMedia()) {
            if (content instanceof AudioBook audioBook) {
                if (audioBook.getNarrator() != null) {
                    narrator.add(audioBook.getNarrator());
                }
            }
        }
        return narrator;
    }

    public Set<MediaContent> findByAuthor(String author) {
        if (author == null) {
            return new HashSet<>();
        }
        Set<MediaContent> result = new HashSet<>();
        for (MediaContent content : catalog.getAllMedia()) {
            if (content instanceof Book book) {
                if (book.getAuthor() != null && book.getAuthor().equals(author)) {
                    result.add(content);
                }
            } else if (content instanceof AudioBook audioBook) {
                if (audioBook.getAuthor() != null && audioBook.getAuthor().equals(author)) {
                    result.add(content);
                }
            }
        }
        return result;
    }
}
