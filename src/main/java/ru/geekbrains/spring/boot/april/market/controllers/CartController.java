package ru.geekbrains.spring.boot.april.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public List<ProductDto> getCartDto() {
        CartDto cartDto=new CartDto(cart);
        return cartDto.getCartDtoItems();
    }

    @GetMapping("/add")
    public int add(@RequestParam Long id) {
        log.info("add to cart product with id=" + id);
        return cart.addItem(productService.findProductByID(id).get());
    }

    @GetMapping("/delete")
    public int deleteProductFromCart(@RequestParam Long id) {
        log.info("delete from cart product with id=" + id);
        return cart.deleteItem(id);
    }

    @GetMapping("/summ")
    public int summCart() {
        return cart.sumItems();
    }
}
