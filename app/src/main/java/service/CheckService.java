package service;


import dao.card.CardDao;
import dao.card.CardDaoCollection;
import dao.card.CardDaoFile;
import dao.product.ProductDao;
import dao.product.ProductDaoCollection;
import dao.product.ProductDaoFile;
import model.Card;
import model.Product;
import utils.Cache;
import utils.CheckCreator;
import writer.CheckConsoleWriter;
import writer.CheckFileWriter;
import writer.CheckWriter;

import java.util.*;


public class CheckService {

    private ProductDao productDaoCollection = new ProductDaoCollection();
    private ProductDao productDaoFile = new ProductDaoFile();
    private CardDao cardDaoCollection = new CardDaoCollection();
    private CardDao cardDaoFile = new CardDaoFile();

    private CheckWriter consoleWriter = new CheckConsoleWriter();
    private CheckWriter fileWriter = new CheckFileWriter();

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

        productQuantityMap = createProductQuantityMap(products);
        createCheck(productQuantityMap, card);

    }

    private boolean productFileExistence() {

        List<String> files = Cache.getFiles();
        return files.stream().anyMatch(file -> file.contains("Products"));
    }

    private boolean cardFileExistence() {

        List<String> files = Cache.getFiles();
        return files.stream().anyMatch(file -> file.contains("Cards"));
    }

    private List<Product> getSelectedProductsFromFile() {

        List<Product> allProducts = productDaoFile.findAll();
        List<Product> selectedProducts = getSelectedProducts(allProducts);
         return selectedProducts;
    }

    private List<Product> getSelectedProductsFromCollection() {

        List<Product> allProducts = productDaoCollection.findAll();
        List<Product> selectedProducts = getSelectedProducts(allProducts);
        return selectedProducts;
    }

    private List<Product> getSelectedProducts(List<Product> allProducts) {

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

    private Optional<Card> getSelectedCardFromFile() {

        if (!isCardPresented()) {
            return Optional.empty();
        }
        Card card = cardDaoFile.getCardByDescription(Cache.getCards().get(0));
        return Optional.of(card);
    }

    private boolean isCardPresented() {
        List<String> cards = Cache.getCards();
        return !cards.isEmpty();
    }

    private Optional<Card> getSelectedCardFromCollection() {
        if (!isCardPresented()) {
            return Optional.empty();
        }
        Card card = cardDaoCollection.getCardByDescription(Cache.getCards().get(0));
        return Optional.of(card);
    }

    private Map<Product, Integer> createProductQuantityMap(List<Product> products) {

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


    private void createCheck(Map<Product, Integer> productQuantity, Optional<Card> card) {
        String check = CheckCreator.createCheck(productQuantity, card);
        consoleWriter.write(check);
        fileWriter.write(check);
    }

}
