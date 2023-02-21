package utils;

import model.Card;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CheckGeneratorTest {

    private CheckGenerator checkGenerator;

    @BeforeEach
    void setUp() {
        checkGenerator = new CheckGenerator();
    }

    @Test
    void checkCalculateTotalPrice() {

        BigDecimal expectedPrice = new BigDecimal("100");
        Map<Product, Integer> map = getProductQuantityMap();
        BigDecimal result = checkGenerator.calculateTotalPrice(map);
        assertEquals(expectedPrice, result);
    }

    @Test
    void checkCalculateTotalPriceWithItemQuantityOver5() {

        Map<Product, Integer> map = getProductQuantityMap();
        BigDecimal expectedPrice = new BigDecimal("190");
        map.put(new Product(4, "Fridge", new BigDecimal(10)), 10);
        BigDecimal result = checkGenerator.calculateTotalPrice(map);
        assertEquals(expectedPrice, result);
    }

    @Test
    void checkCalculateTotalPriceWithCard() {

        BigDecimal total = new BigDecimal("100");
        Card card = getCard();
        BigDecimal expectedResult = new BigDecimal("90");

        BigDecimal result = checkGenerator.calculateTotalPriceWithCard(total, card);
        assertEquals(expectedResult, result);
    }

    @Test
    void checkCalculateTotalPriceWithHighQuantity() {

        BigDecimal total = new BigDecimal("100");
        Product product = getProduct();
        Integer quantity = 10;
        BigDecimal expectedResult = new BigDecimal("190");
        BigDecimal result = checkGenerator.calculateTotalPriceWithHighQuantity(total, product, quantity);
        assertEquals(expectedResult, result);
    }

    @Test
    void checkMultiplyPriceQuantity() {

        BigDecimal price = new BigDecimal("10");
        Integer quantity = 5;
        BigDecimal expectedResult = new BigDecimal("50");
        BigDecimal result = checkGenerator.multiplyPriceQuantity(price, quantity);
        assertEquals(expectedResult, result);
    }

    @Test
    void checkCalculateDiscount() {

        BigDecimal total = new BigDecimal("1000");
        Integer discount = 10;
        BigDecimal expectedResult = new BigDecimal("100");
        BigDecimal result = checkGenerator.calculateDiscount(total, discount);
        assertEquals(expectedResult, result);
    }

    private Map<Product, Integer> getProductQuantityMap() {

        Map<Product, Integer> map = new HashMap<>();
        map.put(new Product(1, "Book", new BigDecimal("5")), 2);
        map.put(new Product(2, "Computer", new BigDecimal("60")), 1);
        map.put(new Product(3, "Chair", new BigDecimal("10")), 3);
        return map;
    }

    private Product getProduct() {
        return new Product(1, "Book", new BigDecimal("10"));
    }

    private Card getCard() {
        return new Card(1, "card-1", 10);
    }
}