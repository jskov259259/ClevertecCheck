package ru.clevertec.check.utils;

import ru.clevertec.check.model.Card;
import ru.clevertec.check.model.Product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class CheckGenerator {

    private static StringBuilder builder = new StringBuilder("Cash Receipt\n");
    private static final Integer CHECK_DISCOUNT = 10;


    public String generateCheck(Map<Product, Integer> productQuantity, Optional<Card> card) {

        builder.append("QTY Description Price TOTAL\n");
        BigDecimal totalPrice = calculateTotalPrice(productQuantity);
        builder.append("-------\n");

        if (card.isPresent()) {
            totalPrice = calculateTotalPriceWithCard(totalPrice, card.get());
        }

        builder.append("Total: " + totalPrice + "$");
        return builder.toString();
    }

    BigDecimal calculateTotalPrice(Map<Product, Integer> productQuantity) {

        BigDecimal totalPrice = new BigDecimal("0");
        totalPrice.setScale(2);
        for (Product product : productQuantity.keySet()) {
            Integer quantity = productQuantity.get(product);
            BigDecimal price = product.getPrice();
            builder.append(quantity + " " + product.getDescription() + " " + price + "$ ");

            if (quantity > 5) {
                totalPrice = calculateTotalPriceWithHighQuantity(totalPrice, product, quantity);
            } else {
                builder.append(multiplyPriceQuantity(price, quantity) + "$\n");
                totalPrice = totalPrice.add(price.multiply(new BigDecimal(quantity)));
            }
        }
        return totalPrice;
    }

    BigDecimal calculateTotalPriceWithCard(BigDecimal total, Card card) {

        BigDecimal discount = calculateDiscount(total, card.getDiscount());
        builder.append("Discount card was presented (" + card.getDescription() + ") with " + card.getDiscount() + "% discount " +
                "(" + discount + "$)\n");
        return total.subtract(discount);
    }

    BigDecimal calculateTotalPriceWithHighQuantity(BigDecimal totalPrice, Product product, Integer quantity) {

        BigDecimal total = multiplyPriceQuantity(product.getPrice(), quantity);
        BigDecimal discount = calculateDiscount(total, CHECK_DISCOUNT);
        total = total.subtract(discount);
        builder.append(total + "$ (10% discount)\n");
        return totalPrice.add(total);
    }

    BigDecimal multiplyPriceQuantity(BigDecimal price, Integer quantity) {

        return price.multiply(new BigDecimal(quantity));
    }

    BigDecimal calculateDiscount(BigDecimal total, Integer discount) {

        return total.multiply(new BigDecimal(discount)).divide(new BigDecimal(100));
    }
}
