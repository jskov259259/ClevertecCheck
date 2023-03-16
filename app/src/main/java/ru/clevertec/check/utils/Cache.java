package ru.clevertec.check.utils;

import java.util.ArrayList;
import java.util.List;

public class Cache {

    private static List<String> pairs = new ArrayList<>();
    private static List<String> cards = new ArrayList<>();
    private static List<String> files = new ArrayList<>();

    public static void savePair(String pair) {
        pairs.add(pair);
    }

    public static void saveFile(String file) {
        files.add(file);
    }

    public static void saveCard(String card) {
        cards.add(card);
    }

    public static List<String> getPairs() {
        return pairs;
    }

    public static List<String> getCards() {
        return cards;
    }

    public static List<String> getFiles() {
        return files;
    }

    public static void clearCards() {
        cards.clear();
    }

    public static void clearFiles() { files.clear(); }

    public static void clearPairs() { pairs.clear(); }
}
