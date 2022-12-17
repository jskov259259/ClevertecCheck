package utils;

import model.Card;
import model.Product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class CheckGenerator {

    public String generateCheck(Map<Product, Integer> productQuantity, Optional<Card> card) {
        StringBuilder builder = new StringBuilder("Cash Receipt\n");
        builder.append("QTY Description Price TOTAL\n");
        BigDecimal totalPrice = new BigDecimal("0");

        for (Product product : productQuantity.keySet()) {
            builder.append(productQuantity.get(product) + " " + product.getDescription() + " " + product.getPrice() + "$ ");

            if (productQuantity.get(product) > 5) {
                BigDecimal total = product.getPrice().multiply(new BigDecimal(productQuantity.get(product)));
                BigDecimal discount = total.multiply(new BigDecimal(10)).divide(new BigDecimal(100));
                total = total.subtract(discount);
                builder.append(total + "$ (10% discount)\n");
                totalPrice = totalPrice.add(total);
            } else {
                builder.append(product.getPrice().multiply(new BigDecimal(productQuantity.get(product))) + "$\n");
                totalPrice = totalPrice.add(product.getPrice().multiply(new BigDecimal(productQuantity.get(product))));
            }

        }
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
}
