package ru.geekbrains.spring.boot.april.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.boot.april.market.dtos.CartDto;
import ru.geekbrains.spring.boot.april.market.dtos.ProductDto;
import ru.geekbrains.spring.boot.april.market.models.Product;
import ru.geekbrains.spring.boot.april.market.services.ProductService;
import ru.geekbrains.spring.boot.april.market.utils.Cart;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final Cart cart;
//    private final CartDto cartDto;
    private final ProductService productService;


    @GetMapping()
    public CartDto getCart() {
        return  new CartDto(cart);
    }

//    @GetMapping("/add")
//    public int add(@RequestParam Long id) {
//        log.info("add to cart product with id=" + id);
//        return cart.addItem(productService.findProductByID(id).get());
//    }

    @GetMapping("/add/{productId}")
    public void addToCart(@PathVariable(name = "productId") Long id) {
       cart.addToCart(id);
    }

    @GetMapping("/quantity")
    public void addToCart(@RequestParam String title, @RequestParam String incDec) {
        cart.quantity(title, incDec);
    }


    @GetMapping("/clear")
    public void clearCart() {
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
