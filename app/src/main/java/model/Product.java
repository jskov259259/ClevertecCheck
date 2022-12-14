package model;

import java.math.BigDecimal;

public class Product {

    private Integer id;

    private String description;

    private BigDecimal price;

    public Product() {
    }

    public Product(Integer id, String description, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }
}
