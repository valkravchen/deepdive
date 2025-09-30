package com.deepdive.test;

import com.deepdive.collection.ViewingSession;
import com.deepdive.model.*;

import java.util.*;

public class TestViewingSession {
    public static void main(String[] args) {
        System.out.println("=== Тест ViewingSession ===\n");
        List<MediaContent> catalog = new ArrayList<>();
        catalog.add(new Book("Книга A", "Автор", 2020));
        catalog.add(new Book("Книга B", "Автор", 2021));
        catalog.add(new Book("Книга C", "Автор", 2022));
        ViewingSession session = new ViewingSession(catalog, "Вечерний просмотр");
        System.out.println("Текущий: " + session.getCurrent().getTitle());
        System.out.println("Есть следующий: " + session.hasNext());
        MediaContent next = session.next();
        System.out.println("После next(): " + next.getTitle());
        System.out.println("Позиция: " + session.getCurrentPosition());
        session.toEnd();
        System.out.println("После toEnd(): " + session.getCurrent().getTitle());
        System.out.println("Есть следующий: " + session.hasNext());
        MediaContent prev = session.previous();
        System.out.println("После previous(): " + prev.getTitle());
        session.reset();
        System.out.println("После reset(): " + session.getCurrent().getTitle());
        System.out.println("\n" + session.getSessionInfo());
        System.out.println("\n✓ ViewingSession работает!");
    }
}

