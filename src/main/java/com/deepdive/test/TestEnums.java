package com.deepdive.test;

import com.deepdive.model.enums.*;

public class TestEnums {
    public static void main(String[] args) {
        System.out.println("=== Тест Enums ===\n");
        System.out.println("MediaType количество: " + MediaType.values().length);
        for (MediaType type : MediaType.values()) {
            System.out.println("  " + type.name() + " -> " + type.getDescription());
        }
        System.out.println("\nContentRating количество: " + ContentRating.values().length);
        for (ContentRating rating : ContentRating.values()) {
            System.out.printf("  %s: %d звёзд - %s%n",
                    rating.name(), rating.getStars(), rating.getDescription());
        }
        System.out.println("\nGenre количество: " + Genre.values().length);
        System.out.println("Первый жанр: " + Genre.NATURE.getDescription());
        System.out.println("Последний жанр: " + Genre.ARCHAEOLOGY.getDescription());

        System.out.println("\n✓ Тест Enums пройден!");
    }
}
