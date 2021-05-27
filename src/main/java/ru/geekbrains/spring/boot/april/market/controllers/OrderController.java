package ru.geekbrains.spring.boot.april.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.boot.april.market.dtos.CartDto;
import ru.geekbrains.spring.boot.april.market.models.Order;
import ru.geekbrains.spring.boot.april.market.services.OrderService;
import ru.geekbrains.spring.boot.april.market.services.ProductService;
import ru.geekbrains.spring.boot.april.market.utils.Cart;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final Cart cart;
    private final OrderService orderService;
    private Principal principal;


//    @GetMapping()
//    public CartDto getCart() {
//        return  new CartDto(cart);
//    }

    @GetMapping("/create")
    public void createOrder(@RequestParam(name="address", defaultValue = "Moscow, Kremlin") String address, @RequestParam(name="phone", defaultValue = "112") String phone) {
        log.info("--The request to create New Order was received by Server successfully.--" );
        orderService.createNewOrder(address, phone, cart);
    }
//
//
//    @GetMapping("/clear")
//    public void clearOrder() {
//      cart.clear();
//    }
//
//    @GetMapping("/delete")
//    public void deleteProductFromCart(@RequestParam String title) {
//        log.info("delete from cart product with title=" + title);
//        cart.deleteItem(title);
//    }
//
//    @GetMapping("/summ")
//    public int summCart() {
//        return cart.sumItems();
//    }
}
