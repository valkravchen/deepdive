package com.deepdive.test;

import com.deepdive.model.MediaContent;
import com.deepdive.model.enums.MediaType;

public class TestMediaContent {
    static class MediaStub extends MediaContent {
        public MediaStub(String title, int year, MediaType type) {
            super(title, year, type);
        }

        @Override
        public String getFormattedInfo() {
            return "Test: " + getTitle();
        }

        @Override
        public double getProgress() {
            return 0.0;
        }

        public static void main(String[] args) {
            System.out.println("=== Тест MediaContent ===\n");
            MediaContent test1 = new MediaStub("Test", 2020, MediaType.BOOK);
            MediaContent test2 = new MediaStub("Test", 2020, MediaType.BOOK);

            // Тест ID
            System.out.println("ID test1: " + test1.getId());
            System.out.println("ID test2: " + test2.getId());
            System.out.println("Разные ID: " + !test1.getId().equals(test2.getId()));
            System.out.println("\nEquals: " + test1.equals(test2));
            System.out.println("HashCode равны: " + (test1.hashCode() == test2.hashCode()));
            test1.addTag("интересное");
            test1.addTag("ИНТЕРЕСНОЕ");  // дубликат в другом регистре
            test1.addTag("  ");  // пустой тег
            test1.addTag(null);   // null
            test1.addTag("важное");
            System.out.println("\nТеги: " + test1.getTags());
            System.out.println("\ntoString(): " + test1);
            System.out.println("\n✓ Тест MediaContent пройден!");
        }
    }
}
