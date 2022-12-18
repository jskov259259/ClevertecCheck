import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import service.CheckService;
import utils.Cache;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ClevertecCheckMainTest {

    @Mock
    private static CheckService checkService;

    @InjectMocks
    private ClevertecCheckMain checkMain;

    @Test
    void testIsArgsCorrectAndSaveCache() {

        assertFalse(checkMain.isPairsPresented());
        String[] pairsArgs = {"1-2", "2-3"};
        boolean result = checkMain.isArgsCorrectAndSaveCache(pairsArgs);
        assertTrue(checkMain.isPairsPresented());
        assertTrue(Cache.getPairs().size() > 0);
        assertTrue(result);
        Cache.clearPairs();
        String[] incorrectPairsArgs = {"1-2.3", "2-3"};
        result = checkMain.isArgsCorrectAndSaveCache(incorrectPairsArgs);
        assertTrue(Cache.getPairs().isEmpty());
        assertFalse(result);

        String[] productsArgs = {"Products.txt"};
        result = checkMain.isArgsCorrectAndSaveCache(productsArgs);
        assertTrue(Cache.getFiles().size() > 0);
        assertTrue(result);
        Cache.clearFiles();
        String[] incorrectProductsArgs = {"Items.txt"};
        result = checkMain.isArgsCorrectAndSaveCache(incorrectProductsArgs);
        assertTrue(Cache.getFiles().isEmpty());
        assertFalse(result);

        String[] cardArgs = {"card-1"};
        result = checkMain.isArgsCorrectAndSaveCache(cardArgs);
        assertTrue(Cache.getCards().size() > 0);
        assertTrue(result);
        Cache.clearCards();
        String[] incorrectCardsArgs = {"card11-2"};
        result = checkMain.isArgsCorrectAndSaveCache(incorrectCardsArgs);
        assertTrue(Cache.getCards().isEmpty());
        assertFalse(result);
        Cache.clearCards();
        String[] manyCards = {"card-1", "card-2"};
        result = checkMain.isArgsCorrectAndSaveCache(manyCards);
        assertFalse(result);
    }

    @Test
    void testIsArgsPresented() {
        String noArgs[] = {};
        assertFalse(checkMain.isArgsPresented(noArgs));
        String args[] = {"1-2", "card-1"};
        assertTrue(checkMain.isArgsPresented(args));
    }

}