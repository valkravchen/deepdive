package com.deepdive.scraper;

import org.jsoup.nodes.Document;

/**
 * Песочница для экспериментов с Jsoup.
 * Здесь можно безопасно пробовать разные URL, селекторы, методы.
 */

public class ScraperPlayground {
    public static void main(String[] args) {
        HtmlParser parser = new HtmlParser();
        String url = "https://books.toscrape.com";
        Document document = parser.loadDocument(url);
//        String body = document.body().select("h3").first().text();
        System.out.println(document.body().select(""));

       }
    }

//    public static void experimentBasicMethods(HtmlParser parser) {
//        System.out.println("--- Базовые методы (Шаг 2) ---");
//        String url = "https://books.toscrape.com";
//        String title = parser.getPageTitle(url);
//        System.out.println("Заголовок: " + title);
//        String body = parser.getBodyText(url);
//        System.out.println("Body (первые 150 символов): " +
//                body.substring(0, Math.min(150, body.length())) + "...");
//        System.out.println(body);
//
//    }

