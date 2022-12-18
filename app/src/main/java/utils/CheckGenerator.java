package utils;

import model.Card;
import model.Product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class CheckGenerator {

    private static StringBuilder builder = new StringBuilder("Cash Receipt\n");


    public String generateCheck(Map<Product, Integer> productQuantity, Optional<Card> card) {

        builder.append("QTY Description Price TOTAL\n");
        BigDecimal totalPrice = calculateTotalPrice(productQuantity);
        builder.append("-------\n");

        if (card.isPresent()) {
            BigDecimal discount = totalPrice.multiply(new BigDecimal(card.get().getDiscount()).divide(new BigDecimal(100)));
            builder.append("Discount card was presented (" + card.get().getDescription() + ") with " + card.get().getDiscount() + "% discount " +
                    "(" + discount + "$)\n");
            totalPrice = totalPrice.subtract(discount);
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
                BigDecimal total = multiplyPriceQuantity(price, quantity);
                BigDecimal discount = total.multiply(new BigDecimal(10)).divide(new BigDecimal(100));
                total = total.subtract(discount);
                builder.append(total + "$ (10% discount)\n");
                totalPrice = totalPrice.add(total);
            } else {
                builder.append(multiplyPriceQuantity(price, quantity) + "$\n");
                totalPrice = totalPrice.add(price.multiply(new BigDecimal(quantity)));
            }
        }
        return totalPrice;
    }

    BigDecimal multiplyPriceQuantity(BigDecimal price, Integer quantity) {

        return price.multiply(new BigDecimal(quantity));
    }
}
