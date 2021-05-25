package ru.geekbrains.spring.boot.april.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.boot.april.market.dtos.CartDto;
import ru.geekbrains.spring.boot.april.market.models.Order;
import ru.geekbrains.spring.boot.april.market.services.OrderService;
import ru.geekbrains.spring.boot.april.market.services.ProductService;
import ru.geekbrains.spring.boot.april.market.utils.Cart;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final Cart cart;
    private final Order order;
    private final OrderService orderService;


    @GetMapping()
    public CartDto getCart() {
        return  new CartDto(cart);
    }

    @GetMapping("/create")
    public void createOrder(Cart cart) {

        orderService.create(cart);
    }


    @GetMapping("/clear")
    public void clearOrder() {
      cart.clear();
    }

    @GetMapping("/delete")
    public void deleteProductFromCart(@RequestParam String title) {
        log.info("delete from cart product with title=" + title);
        cart.deleteItem(title);
    }
//
//    @GetMapping("/summ")
//    public int summCart() {
//        return cart.sumItems();
//    }
}
