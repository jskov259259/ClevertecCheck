package dao.product;

import exceptions.IncorrectValuesNumber;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Cache;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoFileTest {

    private ProductDaoFile productDaoFile;

    @BeforeEach
    void setUp() {
        productDaoFile = new ProductDaoFile();
    }

    @Test
    void checkAddProductToList() {

        String line = "1, Computer, 10";
        List<Product> products = new ArrayList<>();
        productDaoFile.addProductToList(line, products);
        assertTrue(products.size() > 0);
    }

    @Test
    void testCheckCorrectValuesNumber() {

        String[] correctVars = {"1", "Computer", "10"};
        assertDoesNotThrow(() -> productDaoFile.checkCorrectValuesNumber(correctVars));
    }

    @Test
    void testCheckCorrectValuesNumberShouldThrow() {

        String[] incorrectVars = {"1", "Computer"};
        assertThrows(IncorrectValuesNumber.class, () -> productDaoFile.checkCorrectValuesNumber(incorrectVars));
    }

    @Test
    void checkGetPathFromCache() {

        Cache.clearFiles();
        Cache.saveFile("D:\\Products.txt");
        String path = productDaoFile.getPathFromCache();
        assertEquals("D:\\Products.txt", path);
    }
}