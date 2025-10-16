package com.deepdive.scraper;


import org.jsoup.Connection;
import org.jsoup.Jsoup;


import java.io.IOException;

public class SourceChecker {
    public static void main(String[] args) {
        try {
            Connection.Response response = Jsoup.connect("https://www.imdb.com").execute();
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                System.out.println("Код ответа: " + statusCode);
            } else if (statusCode >= 400 && statusCode <= 500) {
                System.out.println("Ошибка клиента (404, 403...)");
            } else if (statusCode >= 500) {
                System.out.println("Ошибка сервера");
            } else {
                System.out.println("Код: " + statusCode);
            }
        } catch (IOException e) {
            System.out.println("Ошибка подключения: " + e.getMessage());
        }
    }
}
