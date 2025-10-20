package com.deepdive.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;

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
    public Document loadDocument(String url) {
        try {
            return Jsoup.connect(url).timeout(DEFAULT_TIMEOUT).get();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Извлекает текст из первого найденного элемента по CSS селектору.
     *
     * @param url         адрес страницы
     * @param cssSelector CSS селектор (например: "p", ".class", "#id")
     * @return текст первого элемента или null если не найден или ошибка
     */
    public String extractText(String url, String cssSelector) {
        // TODO: реализовать
        return null;
    }

    /**
     * Извлекает текст из всех элементов по CSS селектору.
     *
     * @param url         адрес страницы
     * @param cssSelector CSS селектор
     * @return список текстов всех найденных элементов (пустой список если не найдено)
     */
    public List<String> extractAllText(String url, String cssSelector) {
        List<String> result = new ArrayList<>();
        if (!loadDocument(url).select(cssSelector).isEmpty()) {
            for (Element element : loadDocument(url).select(cssSelector)) {
                result.add(element.text());
            }
        }

        // TODO: реализовать
        return new ArrayList<>();
    }

    /**
     * Подсчитывает количество элементов на странице по CSS селектору.
     *
     * @param url         адрес страницы
     * @param cssSelector CSS селектор
     * @return количество найденных элементов (0 если не найдено или ошибка)
     */
    public int countElements(String url, String cssSelector) {
        // TODO: реализовать
        return 0;
    }

    /**
     * Проверяет наличие элемента на странице.
     *
     * @param url         адрес страницы
     * @param cssSelector CSS селектор
     * @return true если элемент найден, false если не найден или ошибка
     */
    public boolean hasElement(String url, String cssSelector) {
        // TODO: реализовать
        return false;
    }
}
