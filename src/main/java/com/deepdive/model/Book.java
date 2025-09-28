package com.deepdive.model;

import com.deepdive.model.enums.MediaType;

public class Book extends MediaContent {
    private String author;               // автор
    private String isbn;                 // ISBN код
    private int totalPages;              // всего страниц
    private int currentPage;             // текущая страница
    private String publisher;            // издательство
    private String language;             // язык издания

    public Book(String title, String author, int year) {
        super(title, year, MediaType.BOOK);
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Автор не может быть пустым");
        }
        this.author = author;
    }

    @Override
    public String getFormattedInfo() {
        return getType().getDescription() + ": " +
                getTitle() + ", " +
                author + " (" +
                getYear() + "), " +
                totalPages + " стр.";
    }

    @Override
    public double getProgress() {
        return (totalPages == 0) ? 0.0 : (double) (currentPage * 100) / totalPages;
    }

    public void updateProgress(int pagesRead) {
        if (pagesRead < 0) {
            throw new IllegalArgumentException("Номер страницы не может быть отрицательным");
        }
        currentPage = pagesRead;
        setCompleted(currentPage >= totalPages && totalPages > 0);
    }


    public int getPagesRemaining() {
        return (currentPage > totalPages) ? 0 : (totalPages - currentPage);
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
        if (totalPages < 0) {
            throw new IllegalArgumentException("Количество страниц не может быть отрицательным");
        }
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage < 0) {
            throw new IllegalArgumentException("Текущая страница не может быть отрицательной");
        }
        this.currentPage = currentPage;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
