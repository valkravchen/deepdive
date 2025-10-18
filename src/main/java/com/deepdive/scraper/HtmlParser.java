package com.deepdive.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Универсальный парсер для извлечения данных из HTML страниц.
 * Используется как основа для всех специализированных парсеров.
 * <p>
 * Этот класс предоставляет базовые методы для загрузки HTML документов
 * и извлечения основной информации (заголовок, текст body).
 */
public class HtmlParser {
    private static final int DEFAULT_TIMEOUT = 5000;

    /**
     * Проверяет возможность загрузки документа по URL.
     *
     * @param url адрес страницы
     * @return true если документ загружается успешно, false при ошибке
     */
    public boolean canLoadDocument(String url) {
        return loadDocument(url) != null;
    }

    /**
     * Получает заголовок страницы (содержимое тега title).
     *
     * @param url адрес страницы
     * @return заголовок страницы или null при ошибке
     */
    public String getPageTitle(String url) {
        Document doc = loadDocument(url);
        return doc != null ? doc.title() : null;
    }

    /**
     * Извлекает весь текстовый контент из тега body.
     *
     * @param url адрес страницы
     * @return текст из body или null при ошибке
     */
    public String getBodyText(String url) {
        Document doc = loadDocument(url);
        return doc != null ? doc.body().text() : null;
    }

    /**
     * Загружает HTML документ по URL.
     *
     * @param url адрес страницы
     * @return Document объект или null при ошибке
     */
    private Document loadDocument(String url) {
        try {
            return Jsoup.connect(url).timeout(DEFAULT_TIMEOUT).get();
        } catch (IOException e) {
            return null;
        }
    }
}
