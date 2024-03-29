package ru.clevertec.check.dao.product;

import ru.clevertec.check.exceptions.IncorrectValuesNumber;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.utils.Cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoFile implements ProductDao {

    private static final String INCORRECT_VALUES_NUMBER = "Incorrect values number in product file.\n" +
            "File must contain: itemId, description, price. Values must be separated by commas.";

    @Override
    public List<Product> findAll() {

        List<Product> products = new ArrayList<>();
        String path = getPathFromCache();

        try {
            addProductsFromFile(path, products);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (IncorrectValuesNumber e) {
            System.out.println(e.getMessage());
        }

        return products;
    }

    private void addProductsFromFile(String path, List<Product> products) throws IOException {

        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while ((line = reader.readLine()) != null) {
            addProductToList(line, products);
        }
        reader.close();
    }

    void addProductToList(String line, List<Product> products) {

        String[] variables =  line.split(",");
        checkCorrectValuesNumber(variables);
        Product product = new Product();
        product.setId(Integer.parseInt(variables[0].trim()));
        product.setDescription(variables[1].trim());
        product.setPrice(new BigDecimal(variables[2].trim()));
        products.add(product);
    }

    void checkCorrectValuesNumber(String[] variables) {

        if (variables.length != 3) {
            throw new IncorrectValuesNumber(INCORRECT_VALUES_NUMBER);
        }
    }

    String getPathFromCache() {

        Optional<String> optionalPath = Cache.getFiles().stream().filter(file -> file.contains("Products")).findFirst();
        return optionalPath.orElse("Products.txt");
    }
}
