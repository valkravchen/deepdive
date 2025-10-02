package com.deepdive.collection;

import com.deepdive.model.MediaContent;

import java.util.*;

/**
 * Навигатор истории просмотра с функциями назад/вперед.
 * Использует два стека для реализации браузерной навигации.
 * <p>
 * Реальное применение:
 * - Browser history (Chrome back/forward)
 * - IDE navigation (IntelliJ IDEA Ctrl+Alt+Left/Right)
 * - File explorer history
 * <p>
 * ПРИМЕЧАНИЕ: ArrayDeque быстрее LinkedList для стека.
 * В реальных браузерах используются более сложные структуры
 * с ограничением размера истории и сохранением на диск.
 */
public class BrowsingNavigator {
    private Deque<MediaContent> backStack;      // История "назад"
    private Deque<MediaContent> forwardStack;   // История "вперед"
    private MediaContent currentPage;            // Текущая страница
    private int maxHistorySize;

    private static final int DEFAULT_MAX_HISTORY = 50;

    // Конструкторы
    public BrowsingNavigator() {
        this.backStack = new ArrayDeque<>();
        this.forwardStack = new ArrayDeque<>();
        this.currentPage = null;
        this.maxHistorySize = DEFAULT_MAX_HISTORY;
    }

    public BrowsingNavigator(int maxHistorySize) {
        this.backStack = new ArrayDeque<>();
        this.forwardStack = new ArrayDeque<>();
        if (maxHistorySize > 0) {
            this.maxHistorySize = maxHistorySize;
        }
    }

    // === НАВИГАЦИЯ ===

    /**
     * Переход на новую страницу.
     *
     * @param page новая страница для посещения
     */
    public void visit(MediaContent page) {
        // TODO: Проверить что page != null
        // TODO: Если currentPage != null:
        //       - Добавить currentPage в backStack через push()
        //       - Если backStack.size() > maxHistorySize, удалить самый старый через removeLast()
        // TODO: Установить currentPage = page
        // TODO: Очистить forwardStack (при новом переходе история "вперед" сбрасывается)

        // ПРИМЕЧАНИЕ: Так же работает браузер - при переходе на новую страницу
        // кнопка "вперед" становится неактивной
    }

    /**
     * Переход назад в истории.
     *
     * @return предыдущая страница или null если истории нет
     */
    public MediaContent back() {
        // TODO: Проверить что backStack не пуст
        // TODO: Если пуст, вернуть currentPage (никуда не идем)
        // TODO: Добавить currentPage в forwardStack через push()
        // TODO: Извлечь из backStack через pop() и установить как currentPage
        // TODO: Вернуть новую currentPage
        return null; // заглушка
    }

    /**
     * Переход вперед в истории.
     *
     * @return следующая страница или null если истории нет
     */
    public MediaContent forward() {
        // TODO: Проверить что forwardStack не пуст
        // TODO: Если пуст, вернуть currentPage (никуда не идем)
        // TODO: Добавить currentPage в backStack через push()
        // TODO: Извлечь из forwardStack через pop() и установить как currentPage
        // TODO: Вернуть новую currentPage
        return null; // заглушка
    }

    // === ПРОВЕРКИ ===

    /**
     * Проверяет, можно ли вернуться назад.
     *
     * @return true если есть история назад
     */
    public boolean canGoBack() {
        return !backStack.isEmpty();
    }

    /**
     * Проверяет, можно ли перейти вперед.
     *
     * @return true если есть история вперед
     */
    public boolean canGoForward() {
        return !forwardStack.isEmpty();
    }

    /**
     * Возвращает текущую страницу.
     *
     * @return текущая страница
     */
    public MediaContent getCurrentPage() {
        return currentPage;
    }

    // === УПРАВЛЕНИЕ ИСТОРИЕЙ ===

    /**
     * Очищает всю историю навигации.
     */
    public void clearHistory() {
        backStack.clear();
        forwardStack.clear();
        currentPage = null;
    }

    /**
     * Возвращает размер истории "назад".
     *
     * @return количество страниц в истории назад
     */
    public int getBackHistorySize() {
       return backStack.size();
    }

    /**
     * Возвращает размер истории "вперед".
     *
     * @return количество страниц в истории вперед
     */
    public int getForwardHistorySize() {
        return forwardStack.size();
    }

    /**
     * Возвращает список истории "назад" (для отображения).
     *
     * @param count количество последних страниц
     * @return список страниц
     */
    public List<MediaContent> getBackHistory(int count) {
        // TODO: Создать результирующий список
        // TODO: Использовать Iterator для backStack
        // TODO: Добавить до count элементов
        // TODO: Вернуть список
        return new ArrayList<>(); // заглушка
    }

    /**
     * Возвращает список истории "вперед" (для отображения).
     *
     * @param count количество страниц
     * @return список страниц
     */
    public List<MediaContent> getForwardHistory(int count) {
        // TODO: Аналогично getBackHistory но для forwardStack
        return new ArrayList<>(); // заглушка
    }

    /**
     * Выводит информацию о навигаторе.
     */
    public void printNavigatorInfo() {
        System.out.println("=== Навигатор ===");
        System.out.println("История назад: " + getBackHistorySize() + " страниц");
        System.out.println("История вперед: " + getForwardHistorySize() + " страниц");

        if (currentPage != null) {
            System.out.println("Текущая страница: " + currentPage.getTitle());
        } else {
            System.out.println("Текущая страница: нет");
        }

        System.out.println("Можно назад: " + (canGoBack() ? "ДА" : "НЕТ"));
        System.out.println("Можно вперед: " + (canGoForward() ? "ДА" : "НЕТ"));
    }
}