package ru.geekbrains.spring.boot.april.market.utils;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.boot.april.market.dtos.ProductDto;
import ru.geekbrains.spring.boot.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.spring.boot.april.market.models.OrderItem;
import ru.geekbrains.spring.boot.april.market.models.Product;
import ru.geekbrains.spring.boot.april.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
@RequiredArgsConstructor
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private BigDecimal sum;

    public void addToCart(Long id) {
        for (OrderItem orderItem: items) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product product = productService.findProductByID(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist with id: " + id + ". An error occurred while executing the procedure: adding an item to the cart. "));
        items.add(new OrderItem(product));
        recalculate();
    }

    private void recalculate() {
        sum = BigDecimal.ZERO;
        for (OrderItem oi: items) {
            sum = sum.add(oi.getPrice());
        }
    }


    public void clear() {
        items.clear();
        recalculate();
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }


    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }
}
