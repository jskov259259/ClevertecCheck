package ru.clevertec.check.dao.card;

import org.junit.jupiter.api.Test;
import ru.clevertec.check.model.Card;

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