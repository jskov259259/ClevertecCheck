package model;

import java.util.Objects;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) && Objects.equals(description, card.description) && Objects.equals(discount, card.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, discount);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                '}';
    }
}
