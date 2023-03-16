package ru.clevertec.check.service;

import org.springframework.stereotype.Service;
import ru.clevertec.check.dao.product.ProductDaoCollection;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.utils.CheckGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemService {

    private ProductDaoCollection productDao = new ProductDaoCollection();
    private CheckGenerator checkGenerator = new CheckGenerator();

    public List<Product> findAllProducts() {
        return productDao.findAll();
    }

    public Product getProductById(Integer id) {
        return productDao.getProductById(id).get();
    }

    public String getCheckByProductIdAndQuantity(Integer id, Integer quantity) {

        Optional<Product> optionalProduct = productDao.getProductById(id);
        if (optionalProduct.isEmpty()) {
            return "Product does not exists";
        }
        Product product = optionalProduct.get();
        Map<Product, Integer> map = new HashMap<>();
        map.put(product, quantity);
        String check = checkGenerator.generateCheck(map, Optional.empty());
        return check;
    }

}
