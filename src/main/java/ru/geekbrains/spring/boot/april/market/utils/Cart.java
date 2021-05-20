package ru.geekbrains.spring.boot.april.market.utils;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.boot.april.market.dtos.ProductDto;
import ru.geekbrains.spring.boot.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.spring.boot.april.market.models.Product;
import ru.geekbrains.spring.boot.april.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Cart {
    private final ProductService productService;
    private List<Product> items;
    private int sum;

    public void addToCart(Long id) {
        Product product=productService.findProductByID(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist with id: " + id + ". An error occurred while executing the procedure: adding an item to the cart. "));
        items.add(product);
    }

    private void recalculate() {
        sum = 0;
        for (Product product : items) {
            sum += product.getPrice();
        }
    }

    public List<Product> getItems() {
        return items;
    }

    public int addItem(Product item) {
        this.items.add(item);
        return sumItems();
    }

    public int deleteItem(Long item) {
        for (Product product : items) {
            if (product.getId().equals(item)) {
                items.remove(product);
                break;
            }
        }
        return sumItems();
    }

    public int sumItems() {
        int result = 0;
        for (Product product : items) {
            result += product.getPrice();
        }
        return result;
    }


    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }
}
