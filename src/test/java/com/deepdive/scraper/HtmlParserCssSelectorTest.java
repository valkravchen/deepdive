package com.deepdive.scraper;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для извлечения данных через CSS селекторы.
 *
 * ЗАДАЧА: Расширить класс HtmlParser методами для работы с CSS селекторами.
 *
 * Тестовый сайт: https://books.toscrape.com - учебный магазин книг
 */
class HtmlParserCssSelectorTest {

    private static final String TEST_URL = "https://books.toscrape.com";

    @Test
    void shouldExtractTextFromFirstElement() {
        HtmlParser parser = new HtmlParser();

        // Извлечь название первой книги
        String bookTitle = parser.extractText(TEST_URL, "h3 a");

        assertNotNull(bookTitle, "Название книги не должно быть null");
        assertFalse(bookTitle.isEmpty(), "Название книги не должно быть пустым");
    }

    @Test
    void shouldExtractTextFromAllElements() {
        HtmlParser parser = new HtmlParser();

        // Извлечь названия всех книг на странице
        List<String> bookTitles = parser.extractAllText(TEST_URL, "h3 a");

        assertNotNull(bookTitles, "Список книг не должен быть null");
        assertFalse(bookTitles.isEmpty(), "Должна найтись хотя бы одна книга");
        assertTrue(bookTitles.size() >= 20, "На главной странице минимум 20 книг");
    }

    @Test
    void shouldCountElements() {
        HtmlParser parser = new HtmlParser();

        // Подсчитать количество карточек книг
        int productCount = parser.countElements(TEST_URL, "article.product_pod");

        assertTrue(productCount >= 20, "На странице должно быть минимум 20 книг");
    }

    @Test
    void shouldCheckElementExists() {
        HtmlParser parser = new HtmlParser();

        // Проверить наличие навигации
        boolean hasNav = parser.hasElement(TEST_URL, "nav");

        assertTrue(hasNav, "На странице должна быть навигация");
    }

    @Test
    void shouldReturnNullWhenElementNotFound() {
        HtmlParser parser = new HtmlParser();

        // Искать несуществующий элемент
        String text = parser.extractText(TEST_URL, ".non-existent-class-12345");

        assertNull(text, "Должен вернуть null если элемент не найден");
    }

    @Test
    void shouldReturnEmptyListWhenNoElements() {
        HtmlParser parser = new HtmlParser();

        // Искать несуществующие элементы
        List<String> texts = parser.extractAllText(TEST_URL, ".non-existent-class-12345");

        assertNotNull(texts, "Список не должен быть null");
        assertTrue(texts.isEmpty(), "Список должен быть пустым");
    }

    @Test
    void shouldReturnZeroWhenNoElements() {
        HtmlParser parser = new HtmlParser();

        // Подсчитать несуществующие элементы
        int count = parser.countElements(TEST_URL, ".non-existent-class-12345");

        assertEquals(0, count, "Должен вернуть 0 если элементов нет");
    }

    @Test
    void shouldHandleConnectionError() {
        HtmlParser parser = new HtmlParser();
        String invalidUrl = "https://invalid-url-that-does-not-exist-12345.com";

        String text = parser.extractText(invalidUrl, "p");

        assertNull(text, "При ошибке соединения должен вернуть null");
    }
}