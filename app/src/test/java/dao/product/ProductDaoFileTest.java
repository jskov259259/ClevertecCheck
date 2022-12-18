package dao.product;

import exceptions.IncorrectValuesNumber;
import model.Card;
import model.Product;
import org.junit.jupiter.api.Test;
import utils.Cache;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoFileTest {

    private ProductDaoFile productDaoFile = new ProductDaoFile();

    @Test
    void testAddProductToList() {

        String line = "1, Computer, 10";
        List<Product> products = new ArrayList<>();
        productDaoFile.addProductToList(line, products);
        assertTrue(products.size() > 0);
    }

    @Test
    void testCheckCorrectValuesNumber() {

        String[] correctVars = {"1", "Computer", "10"};
        assertDoesNotThrow(() -> productDaoFile.checkCorrectValuesNumber(correctVars));
        String[] incorrectVars = {"1", "Computer"};
        assertThrows(IncorrectValuesNumber.class, () -> productDaoFile.checkCorrectValuesNumber(incorrectVars));
    }

    @Test
    void testFetPathFromCache() {

        Cache.clearFiles();
        Cache.saveFile("D:\\Products.txt");
        String path = productDaoFile.getPathFromCache();
        assertEquals("D:\\Products.txt", path);
    }

}