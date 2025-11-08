package com.deepdive.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        Document document = loadDocument(url);
        if (document == null) {
            return null;
        }

        Elements elements = document.select(cssSelector);
        if (elements.isEmpty()) {
            return null;
        }
        return elements.first().text();
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
        Document document = loadDocument(url);
        if (document == null) {
            return result;
        }
        Elements elements = document.select(cssSelector);
        if (elements.isEmpty()) {
            return result;
        }
        for (Element element : elements) {
            result.add(element.text());
        }
        return result;
    }

    /**
     * Подсчитывает количество элементов на странице по CSS селектору.
     *
     * @param url         адрес страницы
     * @param cssSelector CSS селектор
     * @return количество найденных элементов (0 если не найдено или ошибка)
     */
    public int countElements(String url, String cssSelector) {
        Document document = loadDocument(url);
        if (document == null) {
            return 0;
        }
        Elements elements = document.select(cssSelector);
        if (elements.isEmpty()) {
            return 0;
        }
        return elements.size();
    }

    /**
     * Проверяет наличие элемента на странице.
     *
     * @param url         адрес страницы
     * @param cssSelector CSS селектор
     * @return true если элемент найден, false если не найден или ошибка
     */
    public boolean hasElement(String url, String cssSelector) {
        Document document = loadDocument(url);
        if (document == null) {
            return false;
        }
        Elements elements = document.select(cssSelector);
        return !elements.isEmpty();
    }

    /**
     * Извлекает значение атрибута из первого найденного элемента.
     *
     * @param url адрес страницы
     * @param cssSelector CSS селектор
     * @param attributeName имя атрибута (например: "href", "src", "class")
     * @return значение атрибута, пустая строка если атрибут не найден,
     *         или null при ошибке соединения
     */
    public String extractAttribute(String url, String cssSelector, String attributeName) {
        Document document = loadDocument(url);
        if (document == null) {
            return null;
        }
        Elements elements = document.select(cssSelector);
        if (elements.isEmpty()) {
            return "";
        }
        return elements.first().attr(attributeName);
    }

    /**
     * Извлекает значения атрибута из всех найденных элементов.
     *
     * @param url адрес страницы
     * @param cssSelector CSS селектор
     * @param attributeName имя атрибута
     * @return список значений атрибутов (пустой список если не найдено)
     */
    public List<String> extractAllAttributes(String url, String cssSelector, String attributeName) {
        List<String> result =new ArrayList<>();
        Document document = loadDocument(url);
        if (document == null) {
            return result;
        }
        Elements elements = document.select(cssSelector);
        if (elements.isEmpty()) {
            return result;
        }
        for (Element element : elements) {
            result.add(element.attr(attributeName));
        }
        return result;
    }

    /**
     * Извлекает абсолютные URL из ссылок (href атрибутов).
     * Автоматически преобразует относительные URL в абсолютные.
     *
     * @param url адрес страницы
     * @param cssSelector CSS селектор для ссылок (обычно "a" или с классом)
     * @return список абсолютных URL (пустой список если не найдено)
     */
    public List<String> extractLinks(String url, String cssSelector) {
        List<String> result =new ArrayList<>();
        Document document = loadDocument(url);
        if (document == null) {
            return result;
        }
        Elements elements = document.select(cssSelector);
        if (elements.isEmpty()) {
            return result;
        }
        for (Element element : elements) {
            result.add(element.absUrl("href"));
        }
        return result;
    }

    /**
     * Извлекает URL всех изображений на странице.
     *
     * @param url адрес страницы
     * @return список URL изображений (пустой список если не найдено)
     */
    public List<String> extractImages(String url) {
        return extractAllAttributes(url, "img", "src");
    }

    /**
     * Извлекает абсолютные URL всех изображений на странице.
     * Автоматически преобразует относительные URL в абсолютные.
     *
     * @param url адрес страницы
     * @return список абсолютных URL изображений (пустой список если не найдено)
     */
    public List<String> extractAbsoluteImages(String url) {
        // TODO: реализовать
        return new ArrayList<>();
    }
}
