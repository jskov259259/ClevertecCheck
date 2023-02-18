package dao.card;

import model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardDaoCollectionTest {

    private CardDaoCollection cardDaoCollection;

    @BeforeEach
    void setUp() {
        cardDaoCollection = new CardDaoCollection();
    }

    @Test
    void checkGetCardByDescription() {

        //prepare
        String cardDescription = "card-1";
        //action
        Card card = cardDaoCollection.getCardByDescription("card-1");
        //check
        assertNotNull(card);
        assertEquals("card-1", card.getDescription());
    }

    @Test
    void checkGetCardByDescriptionShouldReturnNull() {

        //prepare
        String cardDescription = "card-4";
        //action
        Card nullableCard = cardDaoCollection.getCardByDescription(cardDescription);
        //check
        assertNull(nullableCard);
    }
}