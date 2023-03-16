package ru.clevertec.check.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.service.ItemService;

import java.util.Collection;
import java.util.Optional;

@RestController
public class CheckController {

    private ItemService itemService;

    @Autowired
    public CheckController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value="/products")
    public Collection<Product> findAllProducts() {

        return itemService.findAllProducts();
    }

    @GetMapping(value="/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {

        Optional<Product> product = itemService.getProductById(id);
        if (product.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @GetMapping(value="/check")
    public String getCheckByParams(@RequestParam Integer itemId, @RequestParam Integer quantity) {

        return itemService.getCheckByProductIdAndQuantity(itemId, quantity);
    }
}
