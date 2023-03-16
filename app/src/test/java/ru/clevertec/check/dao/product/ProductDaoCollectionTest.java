package ru.clevertec.check.dao.product;

import org.junit.jupiter.api.Test;
import ru.clevertec.check.model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductDaoCollectionTest {

    private ProductDaoCollection productDaoCollection = new ProductDaoCollection();

    @Test
    void testFindAll() {

        List<Product> products = productDaoCollection.findAll();
        assertNotNull(products);
        assertTrue(products.size() > 0);
    }
}