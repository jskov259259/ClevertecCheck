package dao.product;

import model.Product;

import java.io.FileNotFoundException;
import java.util.List;

public interface ProductDao {

    List<Product> findAll();

}
