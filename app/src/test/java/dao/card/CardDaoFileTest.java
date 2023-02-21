package dao.card;

import exceptions.IncorrectValuesNumber;
import model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Cache;

import static org.junit.jupiter.api.Assertions.*;


class CardDaoFileTest {

    private CardDaoFile cardDaoFile;

    @BeforeEach
    void setUp() {
        cardDaoFile = new CardDaoFile();
    }

    @Test
    void checkIsDescriptionMatches() {

        String line = "2, card-2, 15";
        String description = "card-2";
        assertTrue(cardDaoFile.descriptionMatches(line, description));
        description = "card-3";
        assertFalse(cardDaoFile.descriptionMatches(line, description));
    }

    @Test
    void checkIsDescriptionNotMatches() {

        String line = "2, card-2, 15";
        String description = "card-3";
        assertFalse(cardDaoFile.descriptionMatches(line, description));
    }

    @Test
    void testCheckCorrectValuesNumber() {

        String[] correctVars = {"1", "card-1", "12"};
        assertDoesNotThrow(() -> cardDaoFile.checkCorrectValuesNumber(correctVars));
    }

    @Test
    void testCheckCorrectValuesNumberShouldThrow() {

        String[] incorrectVars = {"1", "card-1"};
        assertThrows(IncorrectValuesNumber.class, () -> cardDaoFile.checkCorrectValuesNumber(incorrectVars));
    }

    @Test
    void checkCreateCard() {

        String line = "1, card-2, 4";
        Card card = cardDaoFile.createCard(line);
        assertNotNull(card);
        assertEquals(1, card.getId());
        assertEquals("card-2", card.getDescription());
        assertEquals(4, card.getDiscount());
    }

    @Test
    void checkGetPathFromCache() {

        Cache.clearFiles();
        Cache.saveFile("D:\\Cards.txt");
        String path = cardDaoFile.getPathFromCache();
        assertEquals("D:\\Cards.txt", path);
    }
}