package ru.geekbrains.spring.boot.april.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.boot.april.market.models.Category;
import ru.geekbrains.spring.boot.april.market.models.Order;
import ru.geekbrains.spring.boot.april.market.models.User;
import ru.geekbrains.spring.boot.april.market.repositories.CategoryRepository;
import ru.geekbrains.spring.boot.april.market.repositories.OrderRepository;
import ru.geekbrains.spring.boot.april.market.utils.Cart;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void createNewOrder(Cart cart) {
        Order order = new Order(cart);
        orderRepository.save(order);

    }
//
//    public Optional<Category> findById(Long id) {
//        return categoryRepository.findById(id);
//    }
//
//    public Optional<Category> findByTitle(String title) {
//        return categoryRepository.findByTitle(title);
//    }
}
