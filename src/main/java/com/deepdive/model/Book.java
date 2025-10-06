package com.deepdive.model;

import com.deepdive.model.enums.MediaType;

public class Book extends MediaContent {
    private String author;
    private String isbn;
    private int totalPages;

    public Book(String title,String author, int year) {
        super(title, year, MediaType.BOOK);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
