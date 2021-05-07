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

    private List<ProductDto> cartDtoItems;

    public CartDto(Cart cart) {
        this.cartDtoItems = cart.getItems().stream().map(ProductDto::new).collect(Collectors.toList());
    }
    @PostConstruct
    public void init() {
        cartDtoItems = new ArrayList<>();
    }

}
