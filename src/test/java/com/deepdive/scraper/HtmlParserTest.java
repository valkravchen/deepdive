package com.deepdive.scraper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для универсального HTML парсера.
 *
 * ЗАДАЧА: Реализовать класс HtmlParser так, чтобы все тесты прошли.
 *
 * ИЗУЧИ: Connection.get(), Document, Document.title(), Document.body()
 */
class HtmlParserTest {

    @Test
    void shouldLoadDocument() {
        HtmlParser parser = new HtmlParser();
        String url = "https://example.com";

        boolean loaded = parser.canLoadDocument(url);

        assertTrue(loaded, "Должен успешно загрузить документ");
    }

    @Test
    void shouldGetPageTitle() {
        HtmlParser parser = new HtmlParser();
        String url = "https://example.com";

        String title = parser.getPageTitle(url);

        assertNotNull(title, "Заголовок страницы не должен быть null");
        assertEquals("Example Domain", title);
    }

    @Test
    void shouldGetBodyText() {
        HtmlParser parser = new HtmlParser();
        String url = "https://example.com";

        String bodyText = parser.getBodyText(url);

        assertNotNull(bodyText, "Текст body не должен быть null");
        assertTrue(bodyText.contains("This domain is for use"),
                "Body должен содержать ожидаемый текст");
    }

    @Test
    void shouldReturnNullOnConnectionError() {
        HtmlParser parser = new HtmlParser();
        String url = "https://invalid-url-12345.com";

        String title = parser.getPageTitle(url);

        assertNull(title, "При ошибке должен вернуть null");
    }

    @Test
    void shouldHandleTimeout() {
        HtmlParser parser = new HtmlParser();
        String url = "http://10.255.255.1"; // IP который не ответит

        String title = parser.getPageTitle(url);

        assertNull(title, "Timeout должен обрабатываться как ошибка");
    }
}