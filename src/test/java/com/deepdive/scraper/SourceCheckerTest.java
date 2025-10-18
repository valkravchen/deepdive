package com.deepdive.scraper;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для проверки доступности источников данных DeepDive.
 *
 * ЗАДАЧА: Реализовать класс SourceChecker так, чтобы все тесты прошли.
 *
 * ИЗУЧИТЕ: Jsoup.connect(), Connection.execute(), Response.statusCode(), Connection.timeout()
 */
class SourceCheckerTest {

    @Test
    void shouldCheckIMDBAvailability() {
        SourceChecker checker = new SourceChecker();
        String url = "https://www.imdb.com";

        boolean isAvailable = checker.isAvailable(url);

        assertTrue(isAvailable, "IMDB должен быть доступен");
    }

    @Test
    void shouldCheckWikipediaAvailability() {
        SourceChecker checker = new SourceChecker();
        String url = "https://www.wikipedia.org";

        boolean isAvailable = checker.isAvailable(url);

        assertTrue(isAvailable, "Wikipedia должна быть доступна");
    }

    @Test
    void shouldReturnStatusCode200() {
        SourceChecker checker = new SourceChecker();
        String url = "https://www.imdb.com";

        int statusCode = checker.getStatusCode(url);

        assertEquals(200, statusCode, "Код 200 означает что сайт доступен");
    }

    @Test
    void shouldHandleInvalidUrl() {
        SourceChecker checker = new SourceChecker();
        String url = "https://this-site-definitely-does-not-exist-xyz123456.com";

        boolean isAvailable = checker.isAvailable(url);

        assertFalse(isAvailable, "Несуществующий сайт должен вернуть false");
    }

    @Test
    void shouldHandleTimeout() {
        SourceChecker checker = new SourceChecker();
        // IP адрес который не отвечает
        String url = "http://10.255.255.1";

        boolean isAvailable = checker.isAvailable(url);

        assertFalse(isAvailable, "Timeout должен обрабатываться как недоступность");
    }
}