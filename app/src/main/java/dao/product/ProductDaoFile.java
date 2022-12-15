package dao.product;

import model.Product;
import utils.Cache;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductDaoFile implements ProductDao {

    @Override
    public List<Product> findAll() {

        List<Product> products = new LinkedList<>();
        String path = getPathFromCache();

        try {
            addProductsFromFile(path, products);
        } catch (IOException e) {
            e.printStackTrace();
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

    private void addProductToList(String line, List<Product> products) {

        String[] variables =  line.split(",");
        Product product = new Product();
        product.setId(Integer.parseInt(variables[0].trim()));
        product.setDescription(variables[1].trim());
        product.setPrice(new BigDecimal(variables[2].trim()));

        products.add(product);
    }


    private String getPathFromCache() {
        Optional<String> optionalPath = Cache.getFiles().stream().filter(file -> file.contains("Products")).findFirst();
        return optionalPath.orElse("Products.txt");
    }
}
