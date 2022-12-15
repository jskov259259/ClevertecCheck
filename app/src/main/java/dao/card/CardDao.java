package dao.card;

import model.Card;

import java.util.List;

public interface CardDao {

    Card getCardByDescription(String description);
}
