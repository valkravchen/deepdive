package com.deepdive.test;

import com.deepdive.model.enums.*;

public class TestProblem1 {
    public static void main(String[] args) {
        System.out.println("=== Тест: Enum ===\n");
        System.out.println("=== MediaType ===");
        for (MediaType type : MediaType.values()) {
            System.out.println(type + " - " + type.getDescription());
        }
        System.out.println("\n=== Genre ===");
        for (Genre genre : Genre.values()) {
            System.out.println(genre + " - " + genre.getDescription());
        }
        System.out.println("\n=== ContentRating ===");
        for (ContentRating contentRating : ContentRating.values()) {
            System.out.println(contentRating + " - " + contentRating.getStars() + " звезд - " +
                    contentRating.getDescription());
        }
        MediaType type = MediaType.DOCUMENTARY;
        System.out.println("\nСоздан тип: " + type);
        System.out.println("Описание: " + type.getDescription());
        System.out.println("\n✅ Enum работают!");
    }
}
