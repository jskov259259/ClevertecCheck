import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Cache;

import static org.junit.jupiter.api.Assertions.*;

class ClevertecCheckMainTest {

    private ClevertecCheckMain checkMain;

    @BeforeEach
    void setUp() {
        checkMain = new ClevertecCheckMain();
    }

    @Test
    void checkIsArgsCorrectAndSaveCachePairs() {

        String[] pairsArgs = {"1-2", "2-3"};
        boolean result = checkMain.isArgsCorrectAndSaveCache(pairsArgs);
        assertTrue(Cache.getPairs().size() > 0);
        assertTrue(result);
    }

    @Test
    void checkIsArgsNotCorrectAndPairsCacheIsEmpty() {

        Cache.clearPairs();
        String[] incorrectPairsArgs = {"1-2.3", "2-3"};
        boolean result = checkMain.isArgsCorrectAndSaveCache(incorrectPairsArgs);
        assertTrue(Cache.getPairs().isEmpty());
        assertFalse(result);
    }

    @Test
    void checkIsArgsCorrectAndSaveCacheFiles() {

        Cache.clearFiles();
        String[] productsArgs = {"Products.txt"};
        boolean result = checkMain.isArgsCorrectAndSaveCache(productsArgs);
        assertTrue(Cache.getFiles().size() > 0);
        assertTrue(result);
    }

    @Test
    void checkIsArgsNotCorrectAndFileCacheIsEmpty() {

        Cache.clearFiles();
        String[] incorrectProductsArgs = {"Items.txt"};
        boolean result = checkMain.isArgsCorrectAndSaveCache(incorrectProductsArgs);
        assertTrue(Cache.getFiles().isEmpty());
        assertFalse(result);
    }

    @Test
    void checkIsArgsCorrectAndSaveCacheCards() {

        Cache.clearCards();
        String[] cardArgs = {"card-1"};
        boolean result = checkMain.isArgsCorrectAndSaveCache(cardArgs);
        assertTrue(Cache.getCards().size() > 0);
        assertTrue(result);
    }

    @Test
    void checkIsArgsInCorrectAndCardCacheIsEmpty() {

        Cache.clearCards();
        String[] incorrectCardsArgs = {"card11-2"};
        boolean result = checkMain.isArgsCorrectAndSaveCache(incorrectCardsArgs);
        assertTrue(Cache.getCards().isEmpty());
        assertFalse(result);
    }

    @Test
    void checkIsArgsInCorrectWithManyCardsAndCardCacheIsEmpty() {

        Cache.clearCards();
        String[] manyCards = {"card-1", "card-2"};
        boolean result = checkMain.isArgsCorrectAndSaveCache(manyCards);
        assertFalse(result);
    }

    @Test
    void testIsArgsPresented() {

        String args[] = {"1-2", "card-1"};
        assertTrue(checkMain.isArgsPresented(args));
    }

    @Test
    void testIsArgsNotPresented() {

        String noArgs[] = {};
        assertFalse(checkMain.isArgsPresented(noArgs));
    }
}