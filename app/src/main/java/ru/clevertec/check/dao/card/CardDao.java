package ru.clevertec.check.dao.card;

import ru.clevertec.check.model.Card;

public interface CardDao {

    Card getCardByDescription(String description);
}
