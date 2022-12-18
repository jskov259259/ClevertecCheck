package dao.card;

import model.Card;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.Cache;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardDaoCollectionTest {

    private CardDaoCollection cardDaoCollection = new CardDaoCollection();

    @Test
    void testGetCardByDescription() {

        Card card = cardDaoCollection.getCardByDescription("card-1");
        assertNotNull(card);
        assertEquals("card-1", card.getDescription());
        Card nullableCard = cardDaoCollection.getCardByDescription("card-4");
        assertNull(nullableCard);
    }
}