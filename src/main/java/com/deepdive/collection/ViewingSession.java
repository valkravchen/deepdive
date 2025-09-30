package com.deepdive.collection;

import com.deepdive.model.MediaContent;

import java.util.List;

public class ViewingSession {
    private List<MediaContent> catalog;
    private int currentPosition;
    private String sessionName;

    public ViewingSession(List<MediaContent> catalog, String sessionName) {
        if (catalog == null || catalog.isEmpty()) {
            throw new IllegalArgumentException("Каталог не может быть null или пустым");
        }
        this.catalog = catalog;
        this.sessionName = sessionName;
        this.currentPosition = 0;
    }


    // Получение текущего элемента
    public MediaContent getCurrent() {
        if (currentPosition >= 0 && currentPosition < catalog.size()) {
            return catalog.get(currentPosition);
        }
        return null; // заглушка
    }

    // Переход к следующему
    public MediaContent next() {
        if (currentPosition < catalog.size() - 1) {
            currentPosition++;
            return catalog.get(currentPosition);
        }
        return null;
    }

    // Переход к предыдущему
    public MediaContent previous() {
        if (currentPosition > 0) {
            currentPosition--;
            return catalog.get(currentPosition);
        }
        return null; // заглушка
    }

    // Проверки
    public boolean hasNext() {
        return currentPosition < catalog.size() - 1;

    }

    public boolean hasPrevious() {
        return currentPosition > 0;
    }

    // Переход к конкретной позиции
    public void jumpTo(int position) {
        if (position < 0 || position >= catalog.size()) {
            throw new IndexOutOfBoundsException("Некорректная позиция: " + position);
        }
        currentPosition = position;
    }

    // Переход в начало
    public void reset() {
        currentPosition = 0;
    }

    // Переход в конец
    public void toEnd() {
        currentPosition = catalog.size() - 1;
    }

    // Информация о сессии
    public String getSessionInfo() {
        // "Сессия: sessionName, позиция: 3/10"
        return "Сессия: " + sessionName + ", позиция: " + (currentPosition + 1) + "/" + getTotalItems(); // заглушка
    }

    // Getters
    public int getCurrentPosition() {
        return currentPosition;
    }

    public int getTotalItems() {
        return catalog.size();
    }
}
