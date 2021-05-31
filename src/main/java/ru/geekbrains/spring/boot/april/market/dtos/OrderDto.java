package ru.geekbrains.spring.boot.april.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.boot.april.market.models.Order;
import ru.geekbrains.spring.boot.april.market.models.OrderItem;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private String description;
    private BigDecimal price;

    public OrderDto(Order order) {
        this.id=order.getId();
        this.price=order.getOrderSum();
        this.description=order.getItems().stream().map(orderItem -> orderItem.getProduct().getTitle() + " x" + orderItem.getQuantity()).collect(Collectors.joining(", "));

    }
}
