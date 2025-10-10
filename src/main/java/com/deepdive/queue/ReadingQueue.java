package com.deepdive.queue;

import com.deepdive.model.Book;

import java.util.Deque;
import java.util.LinkedList;

public class ReadingQueue {
    private Deque<Book> deque;

    public ReadingQueue() {
        this.deque = new LinkedList<>();
    }

    public boolean addBook(Book book, boolean urgent) {
        if (book == null) {
            throw new IllegalArgumentException("book не может быть null");
        }
        if (urgent) {
            return deque.offerFirst(book);
        } else {
            return deque.offerLast(book);
        }
    }

    public Book takeNext() {
        return deque.pollFirst();
    }

    public Book peekNext() {
        return deque.peek();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public int size() {
        return deque.size();
    }

    public void clear() {
        deque.clear();
    }
}