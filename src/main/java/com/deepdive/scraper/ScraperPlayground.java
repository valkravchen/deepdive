package com.deepdive.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Песочница для экспериментов с Jsoup.
 * Здесь можно безопасно пробовать разные URL, селекторы, методы.
 */
public class ScraperPlayground {
    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("https://books.toscrape.com/").get();
            String title = document.title();
            System.out.println("Заголовок страницы: " + title);
            Element h1 = document.selectFirst("h1");
            System.out.println(h1);
            System.out.println("Текст h1: " + h1.text());
            System.out.println("Имя тега: " + h1.tagName());
            Elements allH3 = document.select("h3");
            System.out.println("Найдено заголовков h3: " + allH3.size());
            Elements products = document.select(".product_pod");
            Element firstProduct = products.first();
//            for (Element element: products) {
//                System.out.println(element.text());
//            }
            System.out.println();
            String html = firstProduct.html();
            Elements allLinks = document.select("a");
//            System.out.println(allLinks);
            System.out.println("Всего ссылок на странице: " + allLinks.size());
            Elements bookLinks = document.select("h3 a");
            System.out.println("Ссылок на книги: " + bookLinks.size());
            System.out.println("\nПервые 3 ссылки на книги:");
            for (int i = 0; i < 3 && i < bookLinks.size(); i++) {
                Element link = bookLinks.get(i);
                String href = link.attr("href");
                String titleAttr = link.attr("title");
                System.out.println((i + 1) + ". " + titleAttr);
                System.out.println("   URL: " + href);
                Elements prices = document.select("p.price_color");
                System.out.println("Найдено цен: " + prices.size());
                System.out.println(prices);
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());

        }
    }
}