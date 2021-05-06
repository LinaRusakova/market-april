package ru.geekbrains.spring.boot.april.market.utils;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.boot.april.market.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Cart {

    public List<Product> getItems() {
        return items;
    }

    public void addItem(Product item) {
        this.items.add(item);
    }

    public void deleteItem(Long item) {
        for (Product product : items) {
            if (product.getId().equals(item)) {
                items.remove(product);
                break;
            }
        }
    }

    private List<Product> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }
}
