package com.deepdive.test;

import com.deepdive.collection.BrowsingNavigator;
import com.deepdive.model.*;
import com.deepdive.model.enums.MediaType;

import java.util.List;


public class TestBrowsingNavigator {
    public static void main(String[] args) {
        System.out.println("=== Тест BrowsingNavigator ===\n");

        BrowsingNavigator navigator = new BrowsingNavigator();

        // Создаем тестовые страницы (используем MediaContent)
        MediaContent page1 = new Video("Планета Земля", 2006, MediaType.DOCUMENTARY_SERIES);
        MediaContent page2 = new Book("Sapiens", "Юваль Харари", 2011);
        MediaContent page3 = new Video("Космос", 1980, MediaType.DOCUMENTARY_SERIES);
        MediaContent page4 = new Book("Океаны", "Жак Кусто", 1973);
        MediaContent page5 = new Video("Африка", 2013, MediaType.DOCUMENTARY_SERIES);

        // Тест посещения страниц
        System.out.println("=== Посещение страниц ===");
        navigator.visit(page1);
        System.out.println("Посетили: " + page1.getTitle());

        navigator.visit(page2);
        System.out.println("Посетили: " + page2.getTitle());

        navigator.visit(page3);
        System.out.println("Посетили: " + page3.getTitle());

        System.out.println("Текущая: " + navigator.getCurrentPage().getTitle());
        System.out.println();

        // Тест информации
        navigator.printNavigatorInfo();
        System.out.println();

        // Тест back
        System.out.println("=== Навигация назад ===");
        MediaContent prev = navigator.back();
        System.out.println("После back(): " + (prev != null ? prev.getTitle() : "null"));
        System.out.println("Можно назад: " + navigator.canGoBack());
        System.out.println("Можно вперед: " + navigator.canGoForward());

        prev = navigator.back();
        System.out.println("После еще одного back(): " + (prev != null ? prev.getTitle() : "null"));
        System.out.println();

        // Тест forward
        System.out.println("=== Навигация вперед ===");
        MediaContent next = navigator.forward();
        System.out.println("После forward(): " + (next != null ? next.getTitle() : "null"));
        System.out.println("Можно вперед: " + navigator.canGoForward());
        System.out.println();

        // Тест сброса forward при новом visit
        System.out.println("=== Новое посещение (сброс forward) ===");
        System.out.println("До visit - forward история: " + navigator.getForwardHistorySize());
        navigator.visit(page4);
        System.out.println("Посетили: " + page4.getTitle());
        System.out.println("После visit - forward история: " + navigator.getForwardHistorySize());
        System.out.println("Можно вперед: " + navigator.canGoForward());
        System.out.println();

        // Тест получения истории
        System.out.println("=== История ===");
        navigator.visit(page5);
        List<MediaContent> backHistory = navigator.getBackHistory(3);
        System.out.println("Последние 3 страницы в истории назад:");
        for (int i = 0; i < backHistory.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + backHistory.get(i).getTitle());
        }
        System.out.println();

        // Тест очистки
        System.out.println("=== Очистка истории ===");
        navigator.clearHistory();
        System.out.println("После clearHistory():");
        System.out.println("Back история: " + navigator.getBackHistorySize());
        System.out.println("Forward история: " + navigator.getForwardHistorySize());
        System.out.println("Текущая: " + (navigator.getCurrentPage() != null ?
                navigator.getCurrentPage().getTitle() : "null"));

        System.out.println("\n✓ Тест BrowsingNavigator завершен!");
    }
}

