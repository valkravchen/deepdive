package com.deepdive.scraper;

import org.jsoup.Jsoup;

import java.io.IOException;

public class SourceChecker {
    /**
     * Проверяет доступность сайта по URL.
     *
     * @param url адрес сайта для проверки
     * @return true если сайт доступен (код 200), false при ошибках
     */
    public boolean isAvailable(String url) {
        try {
            int statusCode = Jsoup.connect(url).timeout(5000).execute().statusCode();
            return statusCode == 200;

        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Получает HTTP код ответа от сайта.
     *
     * @param url адрес сайта
     * @return HTTP код ответа (200, 404, 500...) или -1 при ошибке
     */
    public int getStatusCode(String url) {
        try {
            return Jsoup.connect(url).timeout(5000).execute().statusCode();
        } catch (IOException e) {
            return -1;
        }
    }
}
