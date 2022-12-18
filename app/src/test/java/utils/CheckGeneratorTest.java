package utils;

import model.Card;
import model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CheckGeneratorTest {

    private CheckGenerator checkGenerator = new CheckGenerator();

    @Test
    void testCalculateTotalPrice() {

        Map<Product, Integer> map = getProductQuantityMap();
        BigDecimal result = checkGenerator.calculateTotalPrice(map);
        assertEquals(new BigDecimal("100"), result);

        // test for item with quantity > 5
        map.put(new Product(4, "Fridge", new BigDecimal(10)), 10);
        result = checkGenerator.calculateTotalPrice(map);
        assertEquals(new BigDecimal("190"), result);
    }

    @Test
    void testCalculateTotalPriceWithCard() {

        BigDecimal total = new BigDecimal("100");
        Card card = getCard();
        BigDecimal result = checkGenerator.calculateTotalPriceWithCard(total, card);
        assertEquals(new BigDecimal("90"), result);
    }

    @Test
    void testCalculateTotalPriceWithHighQuantity() {

        BigDecimal total = new BigDecimal("100");
        Product product = getProduct();
        Integer quantity = 10;
        BigDecimal result = checkGenerator.calculateTotalPriceWithHighQuantity(total, product, quantity);
        assertEquals(new BigDecimal("190"), result);
    }

    @Test
    void testMultiplyPriceQuantity() {

        BigDecimal price = new BigDecimal("10");
        Integer quantity = 5;
        BigDecimal result = checkGenerator.multiplyPriceQuantity(price, quantity);
        assertEquals(new BigDecimal("50"), result);
    }

    @Test
    void testCalculateDiscount() {

        BigDecimal total = new BigDecimal("1000");
        Integer discount = 10;
        BigDecimal result = checkGenerator.calculateDiscount(total, discount);
        assertEquals(new BigDecimal("100"), result);
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