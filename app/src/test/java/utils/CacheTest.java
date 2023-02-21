package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    @Test
    void checkSavePair() {

        Cache.clearPairs();
        Cache.savePair("2-3");
        assertTrue(Cache.getPairs().size() == 1);
        Cache.savePair("4-6");
        assertTrue(Cache.getPairs().size() == 2);
    }

    @Test
    void checkSaveFile() {

        Cache.clearFiles();
        Cache.saveFile("Products.txt");
        assertTrue(Cache.getFiles().size() == 1);
        Cache.saveFile("Cards.txt");
        assertTrue(Cache.getFiles().size() == 2);
    }

    @Test
    void checkSaveCard() {

        Cache.clearCards();
        Cache.saveCard("card-1");
        assertTrue(Cache.getCards().size() == 1);
        Cache.saveCard("card-2");
        assertTrue(Cache.getCards().size() == 2);
    }

    @Test
    void checkClearPairs() {

        Cache.clearPairs();
        Cache.savePair("2-3");
        assertTrue(Cache.getPairs().size() == 1);
        Cache.clearPairs();
        assertTrue(Cache.getPairs().isEmpty());
    }

    @Test
    void checkClearFiles() {

        Cache.clearFiles();
        Cache.saveFile("Products.txt");
        Cache.saveFile("Cards.txt");
        assertTrue(Cache.getFiles().size() == 2);
        Cache.clearFiles();
        assertTrue(Cache.getFiles().isEmpty());
    }

    @Test
    void checkClearCards() {

        Cache.clearCards();
        Cache.saveCard("card-1");
        assertTrue(Cache.getCards().size() == 1);
        Cache.clearCards();
        assertTrue(Cache.getCards().isEmpty());
    }
}