package com.deepdive.scraper;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для извлечения атрибутов HTML элементов.
 *
 * ЗАДАЧА: Расширить класс HtmlParser методами для работы с атрибутами.
 *
 * Тестовый сайт: https://books.toscrape.com
 */
class HtmlParserAttributesTest {

    private static final String TEST_URL = "https://books.toscrape.com";

    @Test
    void shouldExtractAttributeFromFirstElement() {
        HtmlParser parser = new HtmlParser();

        // Извлечь href первой книги
        String href = parser.extractAttribute(TEST_URL, "h3 a", "href");

        assertNotNull(href, "href не должен быть null");
        assertFalse(href.isEmpty(), "href не должен быть пустым");
        assertTrue(href.contains("catalogue"), "href должен содержать 'catalogue'");
    }

    @Test
    void shouldExtractAttributeFromAllElements() {
        HtmlParser parser = new HtmlParser();

        // Извлечь все href книг
        List<String> hrefs = parser.extractAllAttributes(TEST_URL, "h3 a", "href");

        assertNotNull(hrefs, "Список не должен быть null");
        assertFalse(hrefs.isEmpty(), "Список не должен быть пустым");
        assertTrue(hrefs.size() >= 20, "Должно быть минимум 20 ссылок на книги");

        // Все ссылки должны содержать 'catalogue'
        for (String href : hrefs) {
            assertTrue(href.contains("catalogue"),
                    "Каждая ссылка должна содержать 'catalogue'");
        }
    }

    @Test
    void shouldExtractAbsoluteLinks() {
        HtmlParser parser = new HtmlParser();

        // Извлечь абсолютные URL книг
        List<String> links = parser.extractLinks(TEST_URL, "h3 a");

        assertNotNull(links, "Список не должен быть null");
        assertFalse(links.isEmpty(), "Список не должен быть пустым");

        // Все ссылки должны быть абсолютными (начинаться с http)
        for (String link : links) {
            assertTrue(link.startsWith("http"),
                    "Ссылка должна быть абсолютной: " + link);
            assertTrue(link.contains("books.toscrape.com"),
                    "Ссылка должна содержать домен");
        }
    }

    @Test
    void shouldExtractImageSources() {
        HtmlParser parser = new HtmlParser();

        // Извлечь src всех изображений
        List<String> imageSrcs = parser.extractAllAttributes(TEST_URL, "img", "src");

        assertNotNull(imageSrcs, "Список не должен быть null");
        assertFalse(imageSrcs.isEmpty(), "Должны быть изображения");
        assertTrue(imageSrcs.size() >= 20, "Должно быть минимум 20 обложек книг");
    }

    @Test
    void shouldReturnNullWhenAttributeNotFound() {
        HtmlParser parser = new HtmlParser();

        // Искать несуществующий атрибут
        String attr = parser.extractAttribute(TEST_URL, "h3 a", "non-existent-attr");

        // attr() возвращает пустую строку, не null
        assertNotNull(attr, "attr() возвращает пустую строку");
        assertTrue(attr.isEmpty(), "Несуществующий атрибут должен быть пустой строкой");
    }

    @Test
    void shouldReturnEmptyListWhenNoElements() {
        HtmlParser parser = new HtmlParser();

        // Искать атрибуты несуществующих элементов
        List<String> attrs = parser.extractAllAttributes(
                TEST_URL,
                ".non-existent-class-12345",
                "href"
        );

        assertNotNull(attrs, "Список не должен быть null");
        assertTrue(attrs.isEmpty(), "Список должен быть пустым");
    }

    @Test
    void shouldHandleConnectionError() {
        HtmlParser parser = new HtmlParser();
        String invalidUrl = "https://invalid-url-that-does-not-exist-12345.com";

        String attr = parser.extractAttribute(invalidUrl, "a", "href");

        assertNull(attr, "При ошибке соединения должен вернуть null");
    }
}
