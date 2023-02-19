package dao.card;

import model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CardDaoCollectionTest {

    private CardDaoCollection cardDaoCollection;

    @BeforeEach
    void setUp() {
        cardDaoCollection = new CardDaoCollection();
    }

    @ParameterizedTest
    @ValueSource(strings = {"card-1", "card-2", "card-3"})
    void checkGetCardByDescription(String card) {

        Card resultCard = cardDaoCollection.getCardByDescription(card);

        assertNotNull(resultCard);
        assertEquals(card, resultCard.getDescription());
    }

    @ParameterizedTest
    @ValueSource(strings = {"card-4", "card-5", "card-6"})
    void checkGetCardByDescriptionShouldReturnNull(String card) {

        //action
        Card nullableCard = cardDaoCollection.getCardByDescription(card);
        //check
        assertNull(nullableCard);
    }
}