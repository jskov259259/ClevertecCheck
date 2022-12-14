package model;

public class Card {

    private Integer id;

    private String description;

    private Integer discount;

    public Card() {
    }

    public Card(Integer id, String description, Integer discount) {
        this.id = id;
        this.description = description;
        this.discount = discount;
    }
}
