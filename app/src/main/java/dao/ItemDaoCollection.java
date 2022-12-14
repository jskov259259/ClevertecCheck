package dao;

import model.Card;
import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemDaoCollection implements ItemDao {

    private List<Product> products = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();

    public ItemDaoCollection() {
       initializeProducts();
       initializeCards();
    }

    private void initializeProducts() {
        products.add(new Product(1, "Book", new BigDecimal("12.1")));
        products.add(new Product(2, "Phone", new BigDecimal("85")));
        products.add(new Product(3, "Computer", new BigDecimal("120.5")));
        products.add(new Product(4, "Toy bear", new BigDecimal("5.3")));
        products.add(new Product(5, "Pen", new BigDecimal("1.2")));
        products.add(new Product(6, "Jacket", new BigDecimal("43.2")));
        products.add(new Product(7, "Fridge", new BigDecimal("150.6")));
    }

    private void initializeCards() {
        cards.add(new Card(1, "card-1", 5));
        cards.add(new Card(2, "card-2", 10));
        cards.add(new Card(3, "card-3", 0));
    }


}
