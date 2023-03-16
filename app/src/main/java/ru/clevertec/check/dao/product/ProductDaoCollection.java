package ru.clevertec.check.dao.product;

import ru.clevertec.check.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoCollection implements ProductDao {

    private List<Product> products = new ArrayList<>();


    public ProductDaoCollection() {
       initializeProducts();

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

    @Override
    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> getProductById(Integer id) {

       return products.stream().filter(product -> product.getId() == id).findFirst();
    }
}
