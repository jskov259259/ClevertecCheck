package dao.product;


import model.Product;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoCollectionTest {

    private ProductDaoCollection productDaoCollection = new ProductDaoCollection();

    @Test
    void testFindAll() {

        List<Product> products = productDaoCollection.findAll();
        assertNotNull(products);
        assertTrue(products.size() > 0);
    }
}