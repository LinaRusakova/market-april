package ru.geekbrains.spring.boot.april.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import ru.geekbrains.spring.boot.april.market.utils.Cart;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {

    private List<ProductDto> items;
    private int sum;

    public CartDto(Cart cart) {
        this.items = cart.getItems().stream().map(ProductDto::new).collect(Collectors.toList());
        this.sum = cart.getSum();
    }


}
