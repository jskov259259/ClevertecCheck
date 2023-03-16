package ru.clevertec.check.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.service.ItemService;

import java.util.Collection;

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
    public Product getProductById(@PathVariable Integer id) {

        return itemService.getProductById(id);
    }

    @GetMapping(value="/check")
    public String getCheckByParams(@RequestParam Integer itemId, @RequestParam Integer quantity) {

        return itemService.getCheckByProductIdAndQuantity(itemId, quantity);
    }
}
