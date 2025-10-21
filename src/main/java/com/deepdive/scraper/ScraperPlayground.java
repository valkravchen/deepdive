package com.deepdive.scraper;

import java.util.List;

/**
 * Песочница для экспериментов с Jsoup.
 * Здесь можно безопасно пробовать разные URL, селекторы, методы.
 */
public class ScraperPlayground {

    public static void main(String[] args) {
        HtmlParser parser = new HtmlParser();

        System.out.println("=== Jsoup Песочница ===\n");

        // Эксперимент 1: Базовые методы (Шаг 2)
        experimentBasicMethods(parser);

        // Эксперимент 2: CSS селекторы (Шаг 3)
        experimentCssSelectors(parser);

        // Эксперимент 3: Твои эксперименты
        yourExperiments(parser);
    }

    private static void experimentBasicMethods(HtmlParser parser) {
        System.out.println("--- Базовые методы (Шаг 2) ---");
        String url = "https://books.toscrape.com";

        String title = parser.getPageTitle(url);
        System.out.println("Заголовок: " + title);

        String body = parser.getBodyText(url);
        System.out.println("Body (первые 150 символов): " +
                body.substring(0, Math.min(150, body.length())) + "...");
        System.out.println();
    }

    private static void experimentCssSelectors(HtmlParser parser) {
        System.out.println("--- CSS селекторы (Шаг 3) ---");
        String url = "https://books.toscrape.com";

        // После реализации методов раскомментируй:

        // Первая книга
        String firstBook = parser.extractText(url, "h3 a");
        System.out.println("Первая книга: " + firstBook);

        // Все книги
         List<String> allBooks = parser.extractAllText(url, "h3 a");
         System.out.println("Всего книг: " + allBooks.size());
         System.out.println("Первые 5:");
         for (int i = 0; i < Math.min(5, allBooks.size()); i++) {
             System.out.println((i + 1) + ". " + allBooks.get(i));
         }

        // Количество карточек
         int count = parser.countElements(url, "article.product_pod");
         System.out.println("\nКоличество карточек книг: " + count);

        // Проверка элементов
         System.out.println("Есть навигация? " + parser.hasElement(url, "nav"));
         System.out.println("Есть поиск? " + parser.hasElement(url, "#search"));

        System.out.println("(Раскомментируй код после реализации методов)");
        System.out.println();
    }

    private static void yourExperiments(HtmlParser parser) {
        System.out.println("--- Твои эксперименты ---");

        // Здесь добавляй свои эксперименты:
        // - Разные селекторы
        // - Другие сайты (quotes.toscrape.com, Wikipedia)
        // - Комбинации методов

        System.out.println("Место для твоих экспериментов!");
    }
}
