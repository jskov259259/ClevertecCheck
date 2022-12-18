package dao.card;

import model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDaoCollection implements CardDao {

    private List<Card> cards = new ArrayList<>();

    public CardDaoCollection() {
        initializeCards();
    }

    private void initializeCards() {

        cards.add(new Card(1, "card-1", 5));
        cards.add(new Card(2, "card-2", 10));
        cards.add(new Card(3, "card-3", 0));
    }

    @Override
    public Card getCardByDescription(String description) {

        Optional<Card> card = cards.stream().filter(c -> c.getDescription().equals(description)).findFirst();
        return card.orElse(null);
    }
}
