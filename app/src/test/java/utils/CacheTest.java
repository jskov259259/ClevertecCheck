package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    @Test
    void testSavePair() {

        Cache.clearPairs();
        Cache.savePair("2-3");
        assertTrue(Cache.getPairs().size() == 1);
        Cache.savePair("4-6");
        assertTrue(Cache.getPairs().size() == 2);
    }

    @Test
    void testSaveFile() {

        Cache.clearFiles();
        Cache.saveFile("Products.txt");
        assertTrue(Cache.getFiles().size() == 1);
        Cache.saveFile("Cards.txt");
        assertTrue(Cache.getFiles().size() == 2);
    }

    @Test
    void testSaveCard() {

        Cache.clearCards();
        Cache.saveCard("card-1");
        assertTrue(Cache.getCards().size() == 1);
        Cache.saveCard("card-2");
        assertTrue(Cache.getCards().size() == 2);
    }

    @Test
    void testClearPairs() {

        Cache.clearPairs();
        Cache.savePair("2-3");
        assertTrue(Cache.getPairs().size() == 1);
        Cache.clearPairs();
        assertTrue(Cache.getPairs().isEmpty());
    }

    @Test
    void testClearFiles() {

        Cache.clearFiles();
        Cache.saveFile("Products.txt");
        Cache.saveFile("Cards.txt");
        assertTrue(Cache.getFiles().size() == 2);
        Cache.clearFiles();
        assertTrue(Cache.getFiles().isEmpty());
    }

    @Test
    void testClearCards() {

        Cache.clearCards();
        Cache.saveCard("card-1");
        assertTrue(Cache.getCards().size() == 1);
        Cache.clearCards();
        assertTrue(Cache.getCards().isEmpty());
    }

}