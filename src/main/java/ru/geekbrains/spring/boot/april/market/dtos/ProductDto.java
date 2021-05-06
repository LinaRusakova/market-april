package ru.geekbrains.spring.boot.april.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.boot.april.market.models.Product;

//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;

//    @Size(min = 4, max = 255, message = "Title size: 4-255")
    private String title;

//    @Min(value = 1, message = "Min price = 1")
    private int price;

    private String categoryTitle;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
    }
}
