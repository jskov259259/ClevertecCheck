package dao.product;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoCollectionTest {

    private ProductDaoCollection productDaoCollection;

    @BeforeEach
    void setUp() {
        productDaoCollection = new ProductDaoCollection();
    }

    @Test
    void checkFindAll() {

        List<Product> products = productDaoCollection.findAll();
        assertNotNull(products);
        assertTrue(products.size() > 0);
        assertEquals(1, products.get(0).getId());
    }
}