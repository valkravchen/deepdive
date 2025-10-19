package com.deepdive.scraper;

import java.util.List;

public class TestBooksSelectors {
    public static void main(String[] args) {
        HtmlParser parser = new HtmlParser();
        String url = "https://books.toscrape.com";
        List<String> titles = parser.extractAllText(url, "<h3><a");

        System.out.println("Найдено книг: " + titles.size());
        for (int i = 0; i < Math.min(5, titles.size()); i++) {
            System.out.println((i + 1) + ". " + titles.get(i));
        }
    }
}
