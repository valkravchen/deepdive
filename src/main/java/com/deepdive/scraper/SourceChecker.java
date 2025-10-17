package com.deepdive.scraper;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class SourceChecker {
    public static void checkSource(String name, String url) {
        try {
            long startTime = System.currentTimeMillis();

            Connection.Response response = Jsoup.connect(url)
                    .timeout(5000)
                    .execute();

            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;

            int statusCode = response.statusCode();

            if (statusCode == 200) {
                Document doc = response.parse();
                String title = doc.title();
                System.out.println("[OK] " + name + " доступен (" + responseTime + "ms)");
                System.out.println("     Заголовок: " + title);
            } else if (statusCode >= 400 && statusCode < 500) {
                System.out.println("[WARN] " + name + " ошибка клиента (код: " + statusCode + ", " + responseTime + "ms)");
            } else if (statusCode >= 500) {
                System.out.println("[FAIL] " + name + " ошибка сервера (код: " + statusCode + ", " + responseTime + "ms)");
            } else {
                System.out.println("[INFO] " + name + " код: " + statusCode + " (" + responseTime + "ms)");
            }

        } catch (IOException e) {
            System.out.println("[FAIL] " + name + " недоступен: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Проверка источников DeepDive ===\n");
        checkSource("IMDB", "https://www.imdb.com");
        checkSource("Wikipedia", "https://www.wikipedia.org");
        checkSource("Goodreads", "https://www.goodreads.com");
        checkSource("BBC Planet Earth", "https://www.bbc.co.uk/programmes/b006mywy");
        checkSource("National Geographic", "https://www.nationalgeographic.com");
    }
}



