package com.deepdive.scraper;

public class ParserPlayground {
    public static void main(String[] args) {
        HtmlParser parser = new HtmlParser();
        System.out.println("=== Wikipedia ===");
        String title = parser.getPageTitle("https://en.wikipedia.org/wiki/Documentary_film");
        System.out.println("Заголовок: " + title);
        String body = parser.getBodyText("https://en.wikipedia.org/wiki/Documentary_film");
        System.out.println("\n=== Body ===");
        System.out.println(body.substring(0, Math.min(200, body.length())));
    }
}
