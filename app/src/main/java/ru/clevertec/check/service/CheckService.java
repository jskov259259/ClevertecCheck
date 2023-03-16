package ru.clevertec.check.service;

import ru.clevertec.check.dao.card.CardDao;
import ru.clevertec.check.dao.card.CardDaoCollection;
import ru.clevertec.check.dao.card.CardDaoFile;
import ru.clevertec.check.dao.product.ProductDao;
import ru.clevertec.check.dao.product.ProductDaoCollection;
import ru.clevertec.check.dao.product.ProductDaoFile;
import ru.clevertec.check.model.Card;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.utils.Cache;
import ru.clevertec.check.utils.CheckGenerator;
import ru.clevertec.check.writer.CheckConsoleWriterCreator;
import ru.clevertec.check.writer.CheckFileWriterCreator;
import ru.clevertec.check.writer.CheckWriterCreator;

import java.util.*;

public class CheckService {

    private ProductDao productDaoCollection = new ProductDaoCollection();
    private ProductDao productDaoFile = new ProductDaoFile();
    private CardDao cardDaoCollection = new CardDaoCollection();
    private CardDao cardDaoFile = new CardDaoFile();

    private CheckGenerator checkGenerator = new CheckGenerator();

    private CheckWriterCreator consoleWriter = new CheckConsoleWriterCreator();
    private CheckWriterCreator fileWriter = new CheckFileWriterCreator();

    public void executeData() {

        List<Product> products = new ArrayList<>();
        Optional<Card> card = Optional.empty();
        Map<Product, Integer> productQuantityMap;

        if (productFileExistence()) {
            products = getSelectedProductsFromFile();
        } else {
            products = getSelectedProductsFromCollection();
        }

        if (cardFileExistence()) {
            card = getSelectedCardFromFile();
        } else {
            card = getSelectedCardFromCollection();
        }

        if (products.isEmpty()) {
            return;
        }

        productQuantityMap = createProductQuantityMap(products);
        createCheck(productQuantityMap, card);
    }

    boolean productFileExistence() {

        List<String> files = Cache.getFiles();
        return files.stream().anyMatch(file -> file.contains("Products"));
    }

    boolean cardFileExistence() {

        List<String> files = Cache.getFiles();
        return files.stream().anyMatch(file -> file.contains("Cards"));
    }

    List<Product> getSelectedProductsFromFile() {

        List<Product> allProducts = productDaoFile.findAll();
        List<Product> selectedProducts = getSelectedProducts(allProducts);
        return selectedProducts;
    }

    List<Product> getSelectedProductsFromCollection() {

        List<Product> allProducts = productDaoCollection.findAll();
        List<Product> selectedProducts = getSelectedProducts(allProducts);
        return selectedProducts;
    }

    List<Product> getSelectedProducts(List<Product> allProducts) {

        List<Product> selectedProducts = new LinkedList<>();
        List<String> pairs = Cache.getPairs();
        for (String pair : pairs) {
            String[] idQuantity = pair.split("-");

            for (Product product : allProducts) {
                if (product.getId() == Integer.parseInt(idQuantity[0])) {
                    selectedProducts.add(product);
                }
            }

        }
        return selectedProducts;
    }

    Optional<Card> getSelectedCardFromFile() {

        if (!isCardPresented()) {
            return Optional.empty();
        }
        Card card = cardDaoFile.getCardByDescription(Cache.getCards().get(0));
        if (card == null) {
            System.out.println("No such card found");
        }
        return Optional.ofNullable(card);
    }

    boolean isCardPresented() {

        List<String> cards = Cache.getCards();
        return !cards.isEmpty();
    }

    Optional<Card> getSelectedCardFromCollection() {

        if (!isCardPresented()) {
            return Optional.empty();
        }
        Card card = cardDaoCollection.getCardByDescription(Cache.getCards().get(0));
        if (card == null) {
            System.out.println("No such card found");
        }
        return Optional.ofNullable(card);
    }

    Map<Product, Integer> createProductQuantityMap(List<Product> products) {

        Map<Product, Integer> map = new LinkedHashMap<>();
        List<String> pairs = Cache.getPairs();
        for (String pair : pairs) {
            String[] idQuantity = pair.split("-");
            for (Product product : products) {
                if (product.getId() == Integer.parseInt(idQuantity[0])) {
                    map.put(product, Integer.parseInt(idQuantity[1]));
                }
            }
        }
        return map;
    }


    void createCheck(Map<Product, Integer> productQuantity, Optional<Card> card) {

        String check = checkGenerator.generateCheck(productQuantity, card);
        writeCheck(check);
    }

    void writeCheck(String check) {

        consoleWriter.writeCheck(check);
        fileWriter.writeCheck(check);
    }

}
