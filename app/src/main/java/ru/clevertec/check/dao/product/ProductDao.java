package ru.clevertec.check.dao.product;

import ru.clevertec.check.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

}
